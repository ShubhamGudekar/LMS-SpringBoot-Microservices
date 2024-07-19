package com.lms.borrowing.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lms.borrowing.dto.BookDto;
import com.lms.borrowing.dto.RequestBorrowingDto;
import com.lms.borrowing.dto.ResponseBooksBorrowingDto;
import com.lms.borrowing.dto.ResponseBorrowingDto;
import com.lms.borrowing.entities.Book;
import com.lms.borrowing.entities.Borrowing;
import com.lms.borrowing.entities.Customer;
import com.lms.borrowing.kafka.BorrowEvent;
import com.lms.borrowing.kafka.BorrowingProducer;
import com.lms.borrowing.repo.IBookRepo;
import com.lms.borrowing.repo.IBorrowingRepo;
import com.lms.borrowing.repo.ICustomerRepo;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class BorrowingServiceImpl implements IBorrowingService {

	IBorrowingRepo borrowingRepo;
	IBookRepo bookRepo;
	ICustomerRepo customerRepo;
	BorrowingProducer borrowingProducer;

	@Override
	public ResponseBorrowingDto addBorrowing(RequestBorrowingDto borrowingDto) {

		// Find Book
		Book book = bookRepo.findById(borrowingDto.getBookId())
				.orElseThrow(() -> new RuntimeException("Book not found for id '" + borrowingDto.getBookId() + "'"));

		// Check if book is in stock
		if (book.getQuantity() <= 0) {
			throw new RuntimeException("Book '" + book.getName() + "' is out of Stock.");
		}

		// Find Customer
		Customer customer = customerRepo.findById(borrowingDto.getCustomerId()).orElseThrow(
				() -> new RuntimeException("Customer Not Found for id '" + borrowingDto.getCustomerId() + "'"));

		// Check if customer has borrowed same book
		Optional<Borrowing> borrowing = customer.getBorrowings().stream()
				.filter(b -> b.getBook().getId() == borrowingDto.getBookId() && b.getReturnedDate() == null).findAny();

		if (borrowing.isPresent()) {
			throw new RuntimeException("Customer '" + customer.getFirstname() + "' has already borrowed book '"
					+ book.getName() + "' on date '" + borrowing.get().getBorrowedDate() + "'");
		}

		// Add Borrowing
		Borrowing newBorrowing = new Borrowing(book, customer, LocalDateTime.now());
		newBorrowing = borrowingRepo.save(newBorrowing);

		// Reduce Count of Book
		book.setQuantity(book.getQuantity() - 1);
		bookRepo.save(book);

		// Send borrow closed event
		borrowingProducer.sendMessage(new BorrowEvent("BorrowAdded", newBorrowing.getId(), book.getId(), book.getName(),
				customer.getId(), customer.getFirstname(), customer.getLastname(), customer.getEmailId(),
				newBorrowing.getBorrowedDate()));
		return new ResponseBorrowingDto(newBorrowing.getId(),
				new BookDto(newBorrowing.getBook().getName(), newBorrowing.getBook().getDescription()),
				newBorrowing.getCustomer(), newBorrowing.getBorrowedDate());
	}

	@Override
	public ResponseBorrowingDto closeBorrowing(long borrowingId) {

		// Find borrowing
		Borrowing borrowing = borrowingRepo.findById(borrowingId)
				.orElseThrow(() -> new RuntimeException("Borrowing not registered for id '" + borrowingId + "'"));

		// Set Return Date
		borrowing.setReturnedDate(LocalDateTime.now());
		borrowing = borrowingRepo.save(borrowing);

		// Update Book Count
		Book book = borrowing.getBook();
		book.setQuantity(book.getQuantity() + 1);
		bookRepo.save(book);

		// Send Borrow Closed event
		borrowingProducer.sendMessage(new BorrowEvent("BorrowClosed", borrowingId, borrowing.getReturnedDate()));

		return new ResponseBorrowingDto(borrowing.getId(),
				new BookDto(borrowing.getBook().getName(), borrowing.getBook().getDescription()),
				borrowing.getCustomer(), borrowing.getBorrowedDate(), borrowing.getReturnedDate());
	}

	@Override
	public List<ResponseBooksBorrowingDto> getAllBorrowingByCustomerId(long customerId) {
		return borrowingRepo.findByCustomerId(customerId).stream()
				.map(b -> new ResponseBooksBorrowingDto(b.getId(),
						new BookDto(b.getBook().getName(), b.getBook().getDescription()), b.getBorrowedDate(),
						b.getReturnedDate()))
				.collect(Collectors.toList());
	}

	@Override
	public ResponseBorrowingDto getBorrowingDetailsById(long id) {
		Borrowing borrowing = borrowingRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Borrowing not registered for id '" + id + "'"));
		return new ResponseBorrowingDto(borrowing.getId(),
				new BookDto(borrowing.getBook().getName(), borrowing.getBook().getDescription()),
				borrowing.getCustomer(), borrowing.getBorrowedDate(), borrowing.getReturnedDate());
	}

}
