package com.alibou.ecommerce.orderline;

import org.springframework.stereotype.Service;

import com.alibou.ecommerce.order.Order;

@Service
public class OrderLineMapper {
	
	public OrderLine toOrderLine(OrderLineRequest request) {
		OrderLine orderLine = new OrderLine();
		orderLine.setId(request.id());
		orderLine.setProductId(request.productId());
		orderLine.setQuantity(request.quantity());
		Order order = new Order();
		order.setId(request.orderId());
		orderLine.setOrder(order);
		return orderLine;
	}

	public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
		return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
	}
}
