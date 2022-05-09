package com.iuh.clientnhom8.controller;

import com.iuh.clientnhom8.base.request.BasePageAndSortRequest;
import com.iuh.clientnhom8.entity.Product;
import com.iuh.clientnhom8.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/products")
public class ProductController {
    private Logger logger = Logger.getLogger(getClass().getName());
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/home")
    public String getAllCustomer(Model model, BasePageAndSortRequest basePageAndSortRequest) {
        List<Product> products = productService.getAllProduct(basePageAndSortRequest);
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/productId={id}")
    public String getById(Model model, @PathVariable("id") String id) {
        model.addAttribute("product", productService.getProductById(id));
        return "single-product";
    }
}
