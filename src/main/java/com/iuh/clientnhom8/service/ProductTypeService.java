package com.iuh.clientnhom8.service;

import com.iuh.clientnhom8.entity.Customer;
import com.iuh.clientnhom8.entity.ProductType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductTypeService {
    private final RestTemplate restTemplate;

    @Value("${url_product_type}")
    private String requestUrl;

    public ProductTypeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ProductType> getAllProductType() {
        ResponseEntity<List<ProductType>> response = restTemplate.exchange(requestUrl+"/get-all", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ProductType>>() {
                });
        return response.getBody();
    }
}
