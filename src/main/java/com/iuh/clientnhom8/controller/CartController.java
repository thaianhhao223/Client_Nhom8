package com.iuh.clientnhom8.controller;

import com.iuh.clientnhom8.entity.Cart;
import com.iuh.clientnhom8.entity.Customer;
import com.iuh.clientnhom8.entity.Product;
import com.iuh.clientnhom8.response.LoginInfoResponse;
import com.iuh.clientnhom8.service.CustomerService;
import com.iuh.clientnhom8.service.ProductService;
import com.iuh.clientnhom8.utils.CartUtils;
import com.iuh.clientnhom8.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Controller
public class CartController {
    private Logger logger = Logger.getLogger(getClass().getName());
    private final ProductService productService;
    private final CustomerService customerService;

    public CartController(ProductService productService,
                          CustomerService customerService) {
        this.productService = productService;
        this.customerService = customerService;
    }

    @RequestMapping({ "/shoppingCartRemoveProduct" })
    public String removeProductHandler(HttpServletRequest request, Model model, //
                                       @RequestParam(value = "id", defaultValue = "") String id) {
        Product product = null;
        if (id != null && id.length() > 0) {
            product = productService.getProductById(id);
        }
        if (product != null) {
            Cart cartInfo = CartUtils.getCartInSession(request);
            cartInfo.removeProduct(product);
        }
        return "redirect:/shoppingCart";
    }

    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.POST)
    public String shoppingCartUpdateQty(HttpServletRequest request, Model model,
                                        @ModelAttribute("cartForm") Cart cartForm) {

        Cart cartInfo = CartUtils.getCartInSession(request);
        cartInfo.updateQuantity(cartForm);

        return "redirect:/shoppingCart";
    }

    @RequestMapping(value = {"/shoppingCart"}, method = RequestMethod.GET)
    public String shoppingCartHandler(HttpServletRequest request, Model model) {
        Cart myCart = CartUtils.getCartInSession(request);
        Cart cartForm = CartUtils.getCartInSession(request);

        model.addAttribute("cartForm", myCart); // l???y th??ng tin gi??? h??ng load l??n form
        model.addAttribute("myCart", cartForm); // l???y th??ng tin t??? form ????? c?? th??ng tin gi??? h??ng cu???i c??ng
        return "cart";
    }

    @RequestMapping(value = { "/cart-checkout" }, method = RequestMethod.GET)
    public String checkout(HttpServletRequest request, Model model) {
        Cart cartInfo = CartUtils.getCartInSession(request);

        if (cartInfo == null || cartInfo.isEmpty()) {
            return "redirect:/shoppingCart";
        }
        LoginInfoResponse loginInfoResponse = UserUtils.getUserInfo(request);
        if (loginInfoResponse == null){
            return "redirect:/login";
        }
        Customer customer = customerService.findById(loginInfoResponse.getUserId());
        model.addAttribute("customer", customer);
        model.addAttribute("myCart", cartInfo);
        return "checkout";
    }
}
