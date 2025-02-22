package com.lms.borrowing.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class BorrowingProducer {
	private KafkaTemplate<String, BorrowEvent> bookTemplate;	
	
	public void sendMessage(BorrowEvent event) {
		log.info(event.toString());
		Message<BorrowEvent> message = MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC, "BorrowEvent")
				.build();
		bookTemplate.send(message);
	}
}
