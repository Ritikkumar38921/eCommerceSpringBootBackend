package com.alibou.ecommerce.orderline;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderLineService {

	@Autowired
	private OrderLineRepository repository;
	@Autowired
	private OrderLineMapper mapper;

	public Integer saveOrderLine(OrderLineRequest request) {
		var order = mapper.toOrderLine(request);
		return repository.save(order).getId();
	}

	public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
		return repository.findAllByOrderId(orderId).stream().map(mapper::toOrderLineResponse)
				.collect(Collectors.toList());
	}
}
