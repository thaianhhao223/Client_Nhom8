package com.iuh.clientnhom8.service;

import com.iuh.clientnhom8.entity.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UploadFileService {
    private final RestTemplate restTemplate;

    @Value("${url_upload_file}")
    private String requestUrl;

    public UploadFileService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String uploadFile(MultipartFile multipartFile) {
        ResponseEntity<Object> response = restTemplate.postForEntity(requestUrl+"/upload", multipartFile,
                Object.class);
        return String.valueOf(response.getBody());
    }
}
