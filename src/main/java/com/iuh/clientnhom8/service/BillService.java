package com.iuh.clientnhom8.service;

import com.iuh.clientnhom8.entity.Bill;
import com.iuh.clientnhom8.entity.ProductBrand;
import com.iuh.clientnhom8.request.bill.CreateBillRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BillService {
    private final RestTemplate restTemplate;
    @Value("${url_bill}")
    private String requestUrl;

    public BillService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Bill createBill(CreateBillRequest request) {
        ResponseEntity<Bill> bill = restTemplate.postForEntity(requestUrl + "/save", request, Bill.class);
        return bill.getBody();
    }

    public List<Bill> getAllBill() {
        ResponseEntity<List<Bill>> response = restTemplate.exchange(requestUrl+"/get-all", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Bill>>() {
                });
        return response.getBody();
    }
}
