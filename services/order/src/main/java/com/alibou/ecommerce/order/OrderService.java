package com.alibou.ecommerce.order;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibou.ecommerce.customer.CustomerClient;
import com.alibou.ecommerce.exception.BusinessException;
import com.alibou.ecommerce.kafka.OrderConfirmation;
import com.alibou.ecommerce.kafka.OrderProducer;
import com.alibou.ecommerce.orderline.OrderLineRequest;
import com.alibou.ecommerce.orderline.OrderLineService;
import com.alibou.ecommerce.payment.PaymentClient;
import com.alibou.ecommerce.payment.PaymentRequest;
import com.alibou.ecommerce.product.ProductClient;
import com.alibou.ecommerce.product.PurchaseRequest;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	@Autowired
	private OrderMapper mapper;
	@Autowired
	private CustomerClient customerClient;
	@Autowired
	private PaymentClient paymentClient;
	@Autowired
	private ProductClient productClient;
	@Autowired
	private OrderLineService orderLineService;
	@Autowired
	private OrderProducer orderProducer;

	@Transactional
	public Integer createOrder(OrderRequest request) {
		var customer = customerClient.findCustomerById(request.customerId()).orElseThrow(
				() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));

		var purchasedProducts = productClient.purchaseProducts(request.products());

		var order = this.repository.save(mapper.toOrder(request));

		for (PurchaseRequest purchaseRequest : request.products()) {
			orderLineService.saveOrderLine(
					new OrderLineRequest(null, order.getId(), purchaseRequest.productId(), purchaseRequest.quantity()));
		}
		var paymentRequest = new PaymentRequest(request.amount(), request.paymentMethod(), order.getId(),
				order.getReference(), customer);
		paymentClient.requestOrderPayment(paymentRequest);

		orderProducer.sendOrderConfirmation(new OrderConfirmation(request.reference(), request.amount(),
				request.paymentMethod(), customer, purchasedProducts));

		return order.getId();
	}

	public List<OrderResponse> findAllOrders() {
		return this.repository.findAll().stream().map(this.mapper::fromOrder).collect(Collectors.toList());
	}

	public OrderResponse findById(Integer id) {
		return this.repository.findById(id).map(this.mapper::fromOrder).orElseThrow(
				() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
	}
}
