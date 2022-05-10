package com.iuh.clientnhom8.controller;

import com.iuh.clientnhom8.entity.Cart;
import com.iuh.clientnhom8.entity.Product;
import com.iuh.clientnhom8.service.ProductService;
import com.iuh.clientnhom8.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Controller
public class CartController {
    private Logger logger = Logger.getLogger(getClass().getName());
    private final ProductService productService;

    public CartController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping({ "/shoppingCartRemoveProduct" })
    public String removeProductHandler(HttpServletRequest request, Model model, //
                                       @RequestParam(value = "id", defaultValue = "") String id) {
        Product product = null;
        if (id != null && id.length() > 0) {
            product = productService.getProductById(id);
        }
        if (product != null) {
            Cart cartInfo = Utils.getCartInSession(request);
            cartInfo.removeProduct(product);
        }
        return "redirect:/shoppingCart";
    }

    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.POST)
    public String shoppingCartUpdateQty(HttpServletRequest request, Model model,
                                        @ModelAttribute("cartForm") Cart cartForm) {

        Cart cartInfo = Utils.getCartInSession(request);
        cartInfo.updateQuantity(cartForm);

        return "redirect:/shoppingCart";
    }

    @RequestMapping(value = {"/shoppingCart"}, method = RequestMethod.GET)
    public String shoppingCartHandler(HttpServletRequest request, Model model) {
        Cart myCart = Utils.getCartInSession(request);
        Cart cartForm = Utils.getCartInSession(request);

        model.addAttribute("cartForm", myCart); // lấy thông tin giỏ hàng load lên form
        model.addAttribute("myCart", cartForm); // lấy thông tin từ form để có thông tin giỏ hàng cuối cùng
        return "cart";
    }

    @RequestMapping(value = { "/cart-checkout" }, method = RequestMethod.GET)
    public String checkout(HttpServletRequest request, Model model) {
        Cart cartInfo = Utils.getCartInSession(request);

        if (cartInfo == null || cartInfo.isEmpty()) {
            return "redirect:/shoppingCart";
        }
        // TODO: bổ sung thông tin khách info khách hàng
        model.addAttribute("myCart", cartInfo);
        return "checkout";
    }
}
