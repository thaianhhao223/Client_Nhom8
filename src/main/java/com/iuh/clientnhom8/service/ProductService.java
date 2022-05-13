package com.iuh.clientnhom8.service;

import com.iuh.clientnhom8.base.request.BasePageAndSortRequest;
import com.iuh.clientnhom8.entity.Product;
import com.iuh.clientnhom8.response.ProductListResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
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

    public List<Product> getAllProduct(BasePageAndSortRequest basePageAndSortRequest){
        if (basePageAndSortRequest.getPageNumber() == null){
            basePageAndSortRequest.setPageNumber(0);
        }
        if (basePageAndSortRequest.getPageSize() == null){
            basePageAndSortRequest.setPageSize(12);
        }
        ResponseEntity<ProductListResponse> response = restTemplate.postForEntity(requestUrl + "/get-all", basePageAndSortRequest, ProductListResponse.class);
        return response.getBody().getProductList();
    }

    public Product getProductById(String id){
        ResponseEntity<Product> responseEntity = restTemplate.getForEntity(requestUrl + "/find-by-id/" + id, Product.class);
        return responseEntity.getBody();
    }
}
