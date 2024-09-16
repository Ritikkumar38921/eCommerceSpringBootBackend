package com.alibou.ecommerce.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

//@FeignClient(
//    name = "product-service",
//    url = "${application.config.payment-url}"
//)
@Component
public class PaymentClient {
	
	private String payment_service_endpoint = "http://payment-service/api/v1/payments";
	
	@Autowired
	private RestTemplate restTemplate;

//  @PostMapping
//  Integer requestOrderPayment(@RequestBody PaymentRequest request);
	
	public Integer requestOrderPayment(@RequestBody PaymentRequest request) {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<PaymentRequest> httpEntity = new HttpEntity<PaymentRequest>(request, httpHeaders);
		ResponseEntity<Integer> customerResponse = restTemplate.exchange(payment_service_endpoint ,
				HttpMethod.POST, httpEntity, Integer.class);
		return customerResponse.getBody();
		
	}
}
