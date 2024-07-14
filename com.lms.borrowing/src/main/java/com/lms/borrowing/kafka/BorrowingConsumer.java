package com.lms.borrowing.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.lms.borrowing.repo.IBookRepo;
import com.lms.borrowing.repo.ICustomerRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BorrowingConsumer {

	IBookRepo bookRepo;
	ICustomerRepo customerRepo;

	final String groupId = "Borrow";

	@KafkaListener(topics = "BookEvent", groupId = groupId)
	public void consume(BookEvent event) {

		if (event.getMessage().equals("BookRemoved")) {
			bookRepo.delete(event.getBook());
		} else {
			bookRepo.save(event.getBook());
		}
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