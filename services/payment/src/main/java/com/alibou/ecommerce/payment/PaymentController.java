package com.alibou.ecommerce.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

	@Autowired
	private PaymentService service;

	@PostMapping
	public ResponseEntity<Integer> createPayment(@RequestBody @Valid PaymentRequest request) {
		return ResponseEntity.ok(this.service.createPayment(request));
	}
}
