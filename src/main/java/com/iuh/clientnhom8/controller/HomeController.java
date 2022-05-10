package com.iuh.clientnhom8.controller;

import com.iuh.clientnhom8.base.request.BasePageAndSortRequest;
import com.iuh.clientnhom8.entity.Product;
import com.iuh.clientnhom8.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class HomeController {
    private Logger logger = Logger.getLogger(getClass().getName());
    private ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping({"/", "/home"})
    public String getAllCustomer(Model model, BasePageAndSortRequest basePageAndSortRequest) {
        List<Product> products = productService.getAllProduct(basePageAndSortRequest);
        model.addAttribute("products", products);
        return "index";
    }
//    @RequestMapping(value = "/",method = RequestMethod.GET)
//    public String hello(){
//        return "single-product";
//    }
//    @RequestMapping(value = "/cart",method = RequestMethod.GET)
//    public String cart(){
//        return "cart";
//    }
//    @RequestMapping(value = "/checkout",method = RequestMethod.GET)
//    public String checkout(){
//        return "checkout";
//    }
//    @RequestMapping(value = "/category",method = RequestMethod.GET)
//    public String category(){
//        return "category";
//    }
//    @RequestMapping(value = "/confirmation",method = RequestMethod.GET)
//    public String confirmation(){
//        return "confirmation";
//    }
//    @RequestMapping(value = "/contact",method = RequestMethod.GET)
//    public String contact(){
//        return "contact";
//    }
//    @RequestMapping(value = "/elements",method = RequestMethod.GET)
//    public String elements(){
//        return "elements";
//    }
//    @RequestMapping(value = "/login",method = RequestMethod.GET)
//    public String login(){
//        return "login";
//    }
//    @RequestMapping(value = "/registration",method = RequestMethod.GET)
//    public String registration(){
//        return "registration";
//    }
//    @RequestMapping(value = "/tracking",method = RequestMethod.GET)
//    public String tracking(){
//        return "tracking";
//    }
}

