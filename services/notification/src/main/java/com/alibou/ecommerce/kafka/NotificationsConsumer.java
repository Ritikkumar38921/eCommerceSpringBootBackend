package com.alibou.ecommerce.kafka;

import static java.lang.String.format;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.alibou.ecommerce.email.EmailService;
import com.alibou.ecommerce.kafka.order.OrderConfirmation;
import com.alibou.ecommerce.kafka.payment.PaymentNotificationRequest;
import com.alibou.ecommerce.notification.Notification;
import com.alibou.ecommerce.notification.NotificationRepository;
import com.alibou.ecommerce.notification.NotificationType;

import jakarta.mail.MessagingException;

@Service
public class NotificationsConsumer {

	private static Logger log = LoggerFactory.getLogger(NotificationsConsumer.class);

	@Autowired
	private NotificationRepository repository;

	@Autowired
	private EmailService emailService;

	@KafkaListener(groupId = "paymentGroup", topics = "payment-topic" , containerFactory = "paymentKafkaListenerContainerFactory")
	public void consumePaymentSuccessNotifications(PaymentNotificationRequest paymentConfirmation) throws MessagingException {
		log.info(format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));
		Notification notification = new Notification();
		notification.setNotificationDate(LocalDateTime.now());
		notification.setType(NotificationType.PAYMENT_CONFIRMATION);
		notification.setPaymentConfirmation(paymentConfirmation);
		repository.save(notification);
		var customerName = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();
		emailService.sendPaymentSuccessEmail(paymentConfirmation.customerEmail(), customerName,
				paymentConfirmation.amount(), paymentConfirmation.orderReference());
	}

	@KafkaListener(groupId = "orderGroup", topics = "order-topic" , containerFactory = "orderKafkaListenerContainerFactory")
	public void consumeOrderConfirmationNotifications(OrderConfirmation orderConfirmation) throws MessagingException {
		log.info(format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
		Notification notification = new Notification();
		notification.setNotificationDate(LocalDateTime.now());
		notification.setType(NotificationType.ORDER_CONFIRMATION);
		notification.setOrderConfirmation(orderConfirmation);
		repository.save(notification);
		repository.save(notification);
		var customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();
		emailService.sendOrderConfirmationEmail(orderConfirmation.customer().email(), customerName,
				orderConfirmation.totalAmount(), orderConfirmation.orderReference(), orderConfirmation.products());
	}
	
	
	public void consumeOrderConfirmationNotifications1(OrderConfirmation orderConfirmation) throws MessagingException {
		log.info(format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
		Notification notification = new Notification();
		notification.setNotificationDate(LocalDateTime.now());
		notification.setType(NotificationType.ORDER_CONFIRMATION);
		notification.setOrderConfirmation(orderConfirmation);
		repository.save(notification);
		repository.save(notification);
		var customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();
		emailService.sendOrderConfirmationEmail(orderConfirmation.customer().email(), customerName,
				orderConfirmation.totalAmount(), orderConfirmation.orderReference(), orderConfirmation.products());
	}
}
