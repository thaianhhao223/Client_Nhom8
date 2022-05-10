package com.iuh.clientnhom8.controller;

import com.iuh.clientnhom8.base.request.BasePageAndSortRequest;
import com.iuh.clientnhom8.entity.Cart;
import com.iuh.clientnhom8.entity.Product;
import com.iuh.clientnhom8.request.customer.CreateCustomerRequest;
import com.iuh.clientnhom8.service.AdminService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("admin")
public class AdminController {
    private Logger logger = Logger.getLogger(getClass().getName());

    private final AdminService adminService;



    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/list")
    public String getAllCustomer(Model model) {

        model.addAttribute("customers", adminService.getAllCustomer());
        return "customer-list";
    }

    @GetMapping("/getById={id}")
    public String getById(Model model, @PathVariable("id") String id) {

        model.addAttribute("customer", adminService.findById(id));
        return "customer-detail";
    }

    @PostMapping("/create")
    public String createCustomer(@Valid @ModelAttribute("customer") CreateCustomerRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "customer-form";

        adminService.createCustomer(request);
        return "redirect:list";
    }
    @RequestMapping("/")
    public String getAllProduct(Model model, BasePageAndSortRequest basePageAndSortRequest) {
        List<Product> products = adminService.getAllProduct(basePageAndSortRequest);
        model.addAttribute("products", products);

        return "index";
    }

    @GetMapping("/{id}")
    public String getProductById(Model model, @PathVariable("id") String id) {
        model.addAttribute("product", adminService.getProductById(id));
        return "single-product";
    }



}
