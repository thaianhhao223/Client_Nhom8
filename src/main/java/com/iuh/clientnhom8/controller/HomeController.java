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
    @RequestMapping(value = "/category",method = RequestMethod.GET)
    public String category(){
        return "category";
    }
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
//    @RequestMapping(value = "/tracking",method = RequestMethod.GET)
//    public String tracking(){
//        return "tracking";
//    }
}

