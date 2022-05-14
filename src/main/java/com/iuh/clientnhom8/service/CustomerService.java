package com.iuh.clientnhom8.service;

import java.util.List;

import com.iuh.clientnhom8.entity.Customer;
import com.iuh.clientnhom8.request.customer.CreateCustomerRequest;
import com.iuh.clientnhom8.request.customer.UpdateCustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {
	
	private final RestTemplate restTemplate;
	
	@Value("${url_customer}")
	private String requestUrl;

	public CustomerService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public List<Customer> getAllCustomer() {
		ResponseEntity<List<Customer>> response = restTemplate.exchange(requestUrl+"list", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Customer>>() {
				});
		return response.getBody();
	}
	
	public Customer findById(String id) {
		ResponseEntity<Customer> responseEntity = restTemplate.getForEntity(requestUrl + "/" + id, Customer.class);
		return responseEntity.getBody();
	}
	
	public void createCustomer(CreateCustomerRequest request) {
		restTemplate.postForEntity(requestUrl, request, Customer.class);
	}

	public void updateCustomer(UpdateCustomerRequest request) {

		restTemplate.postForEntity(requestUrl+"update", request, Customer.class);
	}

//	public void updateStatusCustomer(String id) {
//		restTemplate.postForEntity(requestUrl+"update", request, Customer.class);
//	}
}
