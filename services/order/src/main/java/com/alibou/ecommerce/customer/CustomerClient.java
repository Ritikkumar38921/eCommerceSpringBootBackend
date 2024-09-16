package com.alibou.ecommerce.customer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//@FeignClient(
//    name = "customer-service",
//    url = "${application.config.customer-url}"
//)

@Component
public class CustomerClient {

	private String customer_service = "http://customer-service/api/v1/customers/";

	@Autowired
	private RestTemplate restTemplate;

//  @GetMapping("/{customer-id}")
//  Optional<CustomerResponse> findCustomerById(@PathVariable("customer-id") String customerId);

	public Optional<CustomerResponse> findCustomerById(String customerId) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<String> httpEntity = new HttpEntity<String>("", httpHeaders);
		ResponseEntity<CustomerResponse> customerResponse = restTemplate.exchange(customer_service + customerId,
				HttpMethod.GET, httpEntity, CustomerResponse.class);
		return Optional.of(customerResponse.getBody());
	}

}
