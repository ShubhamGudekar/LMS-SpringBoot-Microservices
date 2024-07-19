package com.lms.books.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.lms.books.entities.Book;
import com.lms.books.entities.Borrowing;
import com.lms.books.repo.IBookRepo;
import com.lms.books.repo.IBorrowingRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookConsumer {

	IBookRepo bookRepo;
	IBorrowingRepo borrowingRepo;

	final String groupId = "Book";

	@KafkaListener(topics = "BorrowEvent", groupId = groupId)
	public void consume(BorrowEvent event) {

		Book book = bookRepo.findById(event.getBookId())
				.orElseThrow(() -> new RuntimeException("Book not found: " + event.getBookId()));

		Borrowing borrowing;

		if ("BorrowAdded".equals(event.getMessage())) {

			// Update Book quantity
			book.setQuantity(book.getQuantity() - 1);

			// Create new borrow
			borrowing = new Borrowing(event.getBorrowId(), event.getBookId(), event.getCustomerId(),
					event.getCustomerFirstname(), event.getCustomerLastname(), event.getCustomerEmaild(),
					event.getBorrowedDate());
		} else if ("BorrowClosed".equals(event.getMessage())) {

			// Update Book quantity
			book.setQuantity(book.getQuantity() + 1);

			// Close existing borrow
			borrowing = borrowingRepo.findById(event.getBorrowId())
					.orElseThrow(() -> new RuntimeException("Unable to find borrowing with id " + event.getBorrowId()));
			borrowing.setReturnedDate(event.getReturnedDate());
		} else {
			throw new IllegalArgumentException("Unsupported message type: " + event.getMessage());
		}

		// Save updated book information
		bookRepo.save(book);

		// Add/Update Borrow entry
		borrowingRepo.save(borrowing);
	}

}