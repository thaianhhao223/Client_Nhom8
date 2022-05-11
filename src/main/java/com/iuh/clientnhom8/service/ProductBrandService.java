package com.iuh.clientnhom8.service;

import com.iuh.clientnhom8.entity.ProductBrand;
import com.iuh.clientnhom8.entity.ProductType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductBrandService {
    private final RestTemplate restTemplate;

    @Value("${url_product_brand}")
    private String requestUrl;

    public ProductBrandService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ProductBrand> getAllProductBrand() {
        ResponseEntity<List<ProductBrand>> response = restTemplate.exchange(requestUrl+"/get-all", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ProductBrand>>() {
                });
        return response.getBody();
    }
}
