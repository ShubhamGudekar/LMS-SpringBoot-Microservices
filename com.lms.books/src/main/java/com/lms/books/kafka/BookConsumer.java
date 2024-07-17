package com.lms.books.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.lms.books.entities.Book;
import com.lms.books.entities.Borrowing;
import com.lms.books.repo.IBookRepo;
import com.lms.books.repo.IBorrowingRepo;
import com.lms.books.repo.ICustomerRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookConsumer {

	IBookRepo bookRepo;
	ICustomerRepo customerRepo;
	IBorrowingRepo borrowingRepo;

	final String groupId = "Book";

	@KafkaListener(topics = "BorrowEvent", groupId = groupId)
	public void consume(BorrowEvent event) {

		Book book = bookRepo.findById(event.getBookId()).get();
		if (event.getMessage().equals("BorrowAdded")) {

			// Update Book quantity
			book.setQuantity(book.getQuantity() - 1);
			bookRepo.save(book);

		} else if (event.getMessage().equals("BorrowClosed")) {

			// Update Book quantity
			book.setQuantity(book.getQuantity() + 1);
			bookRepo.save(book);
		}

		// Add/Update Borrow entry
		Borrowing borrowing = new Borrowing(event.getBorrowId(), event.getBookId(), event.getCustomerId(),
				event.getBorrowedDate(), event.getReturnedDate());
		borrowingRepo.save(borrowing);
	}

	@KafkaListener(topics = "CustomerEvent", groupId = groupId)
	public void consume(CustomerEvent event) {

		if (event.getMessage().equals("CustomerRemoved")) {
			customerRepo.delete(event.getCustomer());
		} else {
			customerRepo.save(event.getCustomer());
		}
	}
}