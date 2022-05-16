package com.iuh.clientnhom8.controller;

import com.iuh.clientnhom8.base.request.BasePageAndSortRequest;
import com.iuh.clientnhom8.entity.Product;
import com.iuh.clientnhom8.request.LoginAccountRequest;
import com.iuh.clientnhom8.request.RegisterRequest;
import com.iuh.clientnhom8.response.LoginInfoResponse;
import com.iuh.clientnhom8.service.AccountService;
import com.iuh.clientnhom8.service.CustomerService;
import com.iuh.clientnhom8.service.ProductService;
import com.iuh.clientnhom8.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class HomeController {
    private Logger logger = Logger.getLogger(getClass().getName());
    private final ProductService productService;
    private final CustomerService customerService;
    private final AccountService accountService;

    public HomeController(ProductService productService,
                          CustomerService customerService,
                          AccountService accountService) {
        this.productService = productService;
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @RequestMapping({"/", "/home"})
    public String getAllCustomer(HttpServletRequest request, Model model) {
        int page = 0; //default page number is 0
        int size = 12; //default page size is 12
        BasePageAndSortRequest pageRequest = new BasePageAndSortRequest();
        pageRequest.setPageSize(size);
        pageRequest.setPageNumber(page);
        List<Product> products = productService.getAllProduct(pageRequest);
        model.addAttribute("products", products);
        return "index";
    }

    @RequestMapping(value = "/contact",method = RequestMethod.GET)
    public String contact(HttpServletRequest request, Model model){
        return "contact";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String goToLogin(HttpServletRequest request, Model model){
        LoginInfoResponse loginInfoResponse = UserUtils.getUserInfo(request);
        if (loginInfoResponse != null){
            return "redirect:/home";
        }
        LoginAccountRequest loginForm = new LoginAccountRequest();
        model.addAttribute("loginForm", loginForm);
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model, @ModelAttribute("loginForm") LoginAccountRequest loginAccountRequest){
        LoginInfoResponse loginInfoResponse = accountService.login(loginAccountRequest);
        if (StringUtils.isEmpty(loginInfoResponse.getUserName())){
            return "redirect:/login?error";
        }
        UserUtils.login(request, loginInfoResponse);
        return "redirect:/home";
    }

    @RequestMapping(value = "/registration",method = RequestMethod.GET)
    public String registration(HttpServletRequest request, Model model){
        RegisterRequest registerRequest = new RegisterRequest();
        model.addAttribute("registerForm", registerRequest);
        return "registration";
    }

    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public String registration(HttpServletRequest request, Model model, @ModelAttribute("registerForm") RegisterRequest registerRequest){
        boolean result = accountService.registration(registerRequest);
        if (!result){
            return "error/500";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request, Model model){
        UserUtils.logout(request);
        return "redirect:/login";
    }
}

