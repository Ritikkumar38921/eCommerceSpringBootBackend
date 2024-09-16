package com.alibou.ecommerce;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibou.ecommerce.kafka.NotificationsConsumer;
import com.alibou.ecommerce.kafka.order.Customer;
import com.alibou.ecommerce.kafka.order.OrderConfirmation;
import com.alibou.ecommerce.kafka.order.Product;
import com.alibou.ecommerce.kafka.payment.PaymentMethod;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

	@Autowired
	private NotificationsConsumer notificationsConsumer;

	@GetMapping("/send")
	public void sendNotification() throws MessagingException {
		Customer customer = new Customer("66e25f50c0ef1f1e30580c5c", "Ritik", "Kumar", "ritik.kumar@act21.io");
		Product product = new Product(1, "XYZ", "Descriptionn", new BigDecimal("1000"), 1600000.0);
		List<Product> prodList = new ArrayList<Product>();
		prodList.add(product);
		OrderConfirmation orderConfirmation = new OrderConfirmation("55454", new BigDecimal("160000"),
				PaymentMethod.VISA, customer, prodList);
		notificationsConsumer.consumeOrderConfirmationNotifications1(orderConfirmation);
	}

}
