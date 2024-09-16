package com.alibou.ecommerce.notification;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

	private static Logger log = LoggerFactory.getLogger(NotificationProducer.class);

	@Autowired
	private KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate;

	public void sendNotification(PaymentNotificationRequest request) {
		log.info("Sending notification with body = < {} >", request);
		Message<PaymentNotificationRequest> message = MessageBuilder.withPayload(request)
				.setHeader(TOPIC, "payment-topic").build();
		kafkaTemplate.send(message);
	}
}
