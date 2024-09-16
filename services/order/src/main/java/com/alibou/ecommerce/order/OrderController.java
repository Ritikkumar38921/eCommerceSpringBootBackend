package com.alibou.ecommerce.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

	@Autowired
	private OrderService service;

	@PostMapping
	public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRequest request) {
		return ResponseEntity.ok(service.createOrder(request));
	}

	@GetMapping
	public ResponseEntity<List<OrderResponse>> findAll() {
		return ResponseEntity.ok(service.findAllOrders());
	}

	@GetMapping("/{order-id}")
	public ResponseEntity<OrderResponse> findById(@PathVariable("order-id") Integer orderId) {
		return ResponseEntity.ok(service.findById(orderId));
	}
}