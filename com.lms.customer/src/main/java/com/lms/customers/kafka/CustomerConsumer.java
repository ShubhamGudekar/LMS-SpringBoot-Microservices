package com.lms.customers.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.lms.customers.entities.Borrowing;
import com.lms.customers.repo.IBorrowingRepo;
import com.lms.customers.repo.ICustomerRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerConsumer {

	ICustomerRepo customerRepo;
	IBorrowingRepo borrowingRepo;

	final String groupId = "Customer";

	@KafkaListener(topics = "BorrowEvent", groupId = groupId)
	public void consume(BorrowEvent event) {

		Borrowing borrowing;

		if ("BorrowAdded".equals(event.getMessage())) {

			// Create new borrow
			borrowing = new Borrowing(event.getBorrowId(), event.getBookId(), event.getBookName(),
					event.getCustomerId(), event.getBorrowedDate());
		} else if ("BorrowClosed".equals(event.getMessage())) {

			// Close existing borrow
			borrowing = borrowingRepo.findById(event.getBorrowId())
					.orElseThrow(() -> new RuntimeException("Unable to find borrowing with id " + event.getBorrowId()));
			borrowing.setReturnedDate(event.getReturnedDate());
		} else {
			throw new IllegalArgumentException("Unsupported message type: " + event.getMessage());
		}

		// Add/Update Borrow entry
		borrowingRepo.save(borrowing);

	}
}