package com.iuh.clientnhom8.service;

import com.iuh.clientnhom8.entity.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService {

    private final RestTemplate restTemplate;

    @Value("${url_product}")
    private String requestUrl;

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Product> getAllProduct(){
        ResponseEntity<List<Product>> response = restTemplate.exchange(requestUrl + "/get-all", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Product>>() {
                });
        return response.getBody();
    }

    public Product getProductById(String id){
        ResponseEntity<Product> responseEntity = restTemplate.getForEntity(requestUrl + "/" + id, Product.class);
        return responseEntity.getBody();
    }
}