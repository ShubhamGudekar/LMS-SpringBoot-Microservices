package com.lms.customers.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.lms.customers.entities.Book;
import com.lms.customers.entities.Borrowing;
import com.lms.customers.repo.IBookRepo;
import com.lms.customers.repo.IBorrowingRepo;
import com.lms.customers.repo.ICustomerRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerConsumer {

	IBookRepo bookRepo;
	ICustomerRepo customerRepo;
	IBorrowingRepo borrowingRepo;

	final String groupId = "Customer";

	@KafkaListener(topics = "BookEvent", groupId = groupId)
	public void consume(BookEvent event) {

		if (event.getMessage().equals("BookRemoved")) {
			bookRepo.delete(event.getBook());
		} else {
			bookRepo.save(event.getBook());
		}
	}
	
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
}