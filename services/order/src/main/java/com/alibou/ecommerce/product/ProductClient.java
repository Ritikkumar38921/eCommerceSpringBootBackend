package com.alibou.ecommerce.product;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibou.ecommerce.exception.BusinessException;

@Service
public class ProductClient {

	private String productUrl = "http://product-service/api/v1/products";

	@Autowired
	private RestTemplate restTemplate;

	public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);

		HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestBody, headers);
		ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<>() {
		};
		ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(productUrl + "/purchase", POST,
				requestEntity, responseType);

		if (responseEntity.getStatusCode().isError()) {
			throw new BusinessException(
					"An error occurred while processing the products purchase: " + responseEntity.getStatusCode());
		}
		return responseEntity.getBody();
	}

}
