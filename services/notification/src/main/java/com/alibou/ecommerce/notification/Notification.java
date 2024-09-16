package com.alibou.ecommerce.notification;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.alibou.ecommerce.kafka.order.OrderConfirmation;
import com.alibou.ecommerce.kafka.payment.PaymentNotificationRequest;

@Document(collection = "notification")
public class Notification {

	@Id
	private String id;
	private NotificationType type;
	private LocalDateTime notificationDate;
	private OrderConfirmation orderConfirmation;
	private PaymentNotificationRequest paymentConfirmation;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public LocalDateTime getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(LocalDateTime notificationDate) {
		this.notificationDate = notificationDate;
	}

	public OrderConfirmation getOrderConfirmation() {
		return orderConfirmation;
	}

	public void setOrderConfirmation(OrderConfirmation orderConfirmation) {
		this.orderConfirmation = orderConfirmation;
	}

	public PaymentNotificationRequest getPaymentConfirmation() {
		return paymentConfirmation;
	}

	public void setPaymentConfirmation(PaymentNotificationRequest paymentConfirmation) {
		this.paymentConfirmation = paymentConfirmation;
	}

}
