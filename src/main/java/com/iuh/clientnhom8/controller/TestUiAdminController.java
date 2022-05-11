package com.iuh.clientnhom8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin-test-ui")
public class TestUiAdminController {
    @GetMapping("/home")
    public String homeAdmin(){
        return "dashboard-admin";
    }
    @GetMapping("/account")
    public String accountAdmin(){
        return "account-admin";
    }
    @GetMapping("/customer")
    public String customerAdmin(){
        return "customer-admin";
    }
    @GetMapping("/type")
    public String typeAdmin(){
        return "type-admin";
    }
    @GetMapping("/brand")
    public String brandAdmin(){
        return "brand-admin";
    }
    @GetMapping("/product")
    public String productAdmin(){
        return "product-admin";
    }
    @GetMapping("/billing")
    public String billAdmin(){
        return "billing-admin";
    }
    @GetMapping("/customer/customer-order")
    public String customerOrderAdmin(){
        return "customer-order-admin";
    }
    @GetMapping("/profile")
    public String profileAdmin(){
        return "profile-admin";
    }
    @GetMapping("/signin")
    public String signinAdmin(){
        return "sign-in-admin";
    }
    @GetMapping("/signup")
    public String signupAdmin(){
        return "sign-up-admin";
    }
}
