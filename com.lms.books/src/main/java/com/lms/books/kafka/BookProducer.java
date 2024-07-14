package com.lms.books.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookProducer {
	private KafkaTemplate<String, BookEvent> kafkaTemplate;

	public void sendMessage(BookEvent event) {

		System.out.println(event);

		// create Message
		Message<BookEvent> message = MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC, "BookEvent")
				.build();
		kafkaTemplate.send(message);
	}
}
