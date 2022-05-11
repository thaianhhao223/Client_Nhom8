package com.iuh.clientnhom8.service;

import com.iuh.clientnhom8.entity.Bill;
import com.iuh.clientnhom8.request.bill.CreateBillRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
}
