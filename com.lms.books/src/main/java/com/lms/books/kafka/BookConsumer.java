package com.lms.books.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.lms.books.repo.IBookRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookConsumer {

	IBookRepo bookRepo;

	final String groupId = "Book";

	@KafkaListener(topics = "BorrowEvent", groupId = groupId)
	public void consume(BookEvent event) {
		bookRepo.save(event.getBook());
	}

}