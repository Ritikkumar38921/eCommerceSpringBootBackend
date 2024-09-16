package com.alibou.ecommerce.kafka;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
public class OrderProducer {

	private static Logger log = LoggerFactory.getLogger(OrderProducer.class);
	
	@Autowired
	private KafkaTemplate<String, OrderConfirmation> kafkaTemplate;

	public void sendOrderConfirmation(OrderConfirmation orderConfirmation) {
		log.info("Sending order confirmation");
		Message<OrderConfirmation> message = MessageBuilder.withPayload(orderConfirmation)
				.setHeader(TOPIC, "order-topic").build();

		kafkaTemplate.send(message);
	}
}
