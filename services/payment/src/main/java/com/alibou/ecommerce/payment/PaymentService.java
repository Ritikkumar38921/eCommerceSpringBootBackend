package com.alibou.ecommerce.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibou.ecommerce.notification.NotificationProducer;
import com.alibou.ecommerce.notification.PaymentNotificationRequest;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository repository;
	@Autowired
	private PaymentMapper mapper;
	@Autowired
	private NotificationProducer notificationProducer;

	public Integer createPayment(PaymentRequest request) {
		var payment = this.repository.save(this.mapper.toPayment(request));

		this.notificationProducer.sendNotification(
				new PaymentNotificationRequest(request.orderReference(), request.amount(), request.paymentMethod(),
						request.customer().firstname(), request.customer().lastname(), request.customer().email()));
		return payment.getId();
	}
}
