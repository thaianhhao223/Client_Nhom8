package com.iuh.clientnhom8.service;

import java.util.List;

import com.iuh.clientnhom8.base.request.BasePageAndSortRequest;
import com.iuh.clientnhom8.entity.Customer;
import com.iuh.clientnhom8.entity.Product;
import com.iuh.clientnhom8.request.customer.CreateCustomerRequest;
import com.iuh.clientnhom8.response.ProductListResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdminService {
    private RestTemplate restTemplate;

    @Value("${url_admin}")
    private String requestUrl;

    public AdminService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<Customer> getAllCustomer() {
        ResponseEntity<List<Customer>> response = restTemplate.exchange(requestUrl, HttpMethod.GET, null,
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

    public List<Product> getAllProduct(BasePageAndSortRequest basePageAndSortRequest){
        if (basePageAndSortRequest.getPageNumber() == null || basePageAndSortRequest.getPageSize() == null
                || basePageAndSortRequest.getSort() == null){
            basePageAndSortRequest.setPageSize(12);
            basePageAndSortRequest.setPageNumber(0);
        }
        ResponseEntity<ProductListResponse> response = restTemplate.postForEntity(requestUrl + "/get-all", basePageAndSortRequest, ProductListResponse.class);
        return response.getBody().getProductList();
    }

    public Product getProductById(String id){
        ResponseEntity<Product> responseEntity = restTemplate.getForEntity(requestUrl + "/find-by-id/" + id, Product.class);
        return responseEntity.getBody();
    }
}
