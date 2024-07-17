package com.lms.customers.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerProducer {
	private KafkaTemplate<String, CustomerEvent> kafkaTemplate;

	public void sendMessage(CustomerEvent event) {

		Message<CustomerEvent> message = MessageBuilder.withPayload(event)
				.setHeader(KafkaHeaders.TOPIC, "CustomerEvent").build();
		kafkaTemplate.send(message);
	}
}
