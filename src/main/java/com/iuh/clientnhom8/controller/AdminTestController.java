package com.iuh.clientnhom8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("admin-test")
public class AdminTestController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String hello(){
        return "single-product";
    }
    @RequestMapping(value = "/cart",method = RequestMethod.GET)
    public String cart(){
        return "cart";
    }
    @RequestMapping(value = "/checkout",method = RequestMethod.GET)
    public String checkout(){
        return "checkout";
    }
    @RequestMapping(value = "/category",method = RequestMethod.GET)
    public String category(){
        return "category";
    }
    @RequestMapping(value = "/confirmation",method = RequestMethod.GET)
    public String confirmation(){
        return "confirmation";
    }
    @RequestMapping(value = "/contact",method = RequestMethod.GET)
    public String contact(){
        return "contact";
    }
    @RequestMapping(value = "/elements",method = RequestMethod.GET)
    public String elements(){
        return "elements";
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }
    @RequestMapping(value = "/registration",method = RequestMethod.GET)
    public String registration(){
        return "registration";
    }
    @RequestMapping(value = "/tracking",method = RequestMethod.GET)
    public String tracking(){
        return "tracking";
    }
}

