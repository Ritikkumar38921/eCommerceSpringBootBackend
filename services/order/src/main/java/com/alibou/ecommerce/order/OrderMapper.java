package com.alibou.ecommerce.order;

import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

	public Order toOrder(OrderRequest request) {
		if (request == null) {
			return null;
		}
		Order order = new Order();
		order.setReference(request.reference());
		order.setCustomerId(request.customerId());
		order.setTotalAmount(request.amount());
		order.setPaymentMethod(request.paymentMethod());
		return order;
	}

	public OrderResponse fromOrder(Order order) {
		return new OrderResponse(order.getId(), order.getReference(), order.getTotalAmount(), order.getPaymentMethod(),
				order.getCustomerId());
	}
}
