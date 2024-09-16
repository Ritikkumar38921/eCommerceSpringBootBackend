package com.alibou.ecommerce.customer;

import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

	public Customer toCustomer(CustomerRequest request) {
		if (request == null) {
			return null;
		}
		Customer customer = new Customer();
		customer.setEmail(request.email());
		customer.setFirstname(request.firstname());
		customer.setLastname(request.lastname());
		customer.setAddress(request.address());
		return customer;
	}

	public CustomerResponse fromCustomer(Customer customer) {
		if (customer == null) {
			return null;
		}
		return new CustomerResponse(customer.getId(), customer.getFirstname(), customer.getLastname(),
				customer.getEmail(), customer.getAddress());
	}
}
