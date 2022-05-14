package com.iuh.clientnhom8.service;

import com.iuh.clientnhom8.entity.Account;
import com.iuh.clientnhom8.request.LoginAccountRequest;
import com.iuh.clientnhom8.request.RegisterRequest;
import com.iuh.clientnhom8.response.LoginInfoResponse;
import com.iuh.clientnhom8.utils.UserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountService {
    private final RestTemplate restTemplate;

    @Value("${url_account}")
    private String requestUrl;

    public AccountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean registration(RegisterRequest registerRequest){
        Account account = restTemplate.postForEntity(requestUrl + "/registration", registerRequest, Account.class).getBody();
        if (account == null){
            return false;
        }
        return true;
    }

    public LoginInfoResponse login(LoginAccountRequest loginAccountRequest){
        LoginInfoResponse loginInfoResponse = restTemplate.postForEntity(requestUrl + "/login", loginAccountRequest, LoginInfoResponse.class).getBody();
        return loginInfoResponse;
    }
}
