package com.iuh.clientnhom8.controller;

import com.iuh.clientnhom8.base.request.BasePageAndSortRequest;
import com.iuh.clientnhom8.entity.Cart;
import com.iuh.clientnhom8.entity.Product;

import com.iuh.clientnhom8.service.ProductService;
import com.iuh.clientnhom8.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("/")
    public String getAllProduct(HttpServletRequest request, Model model, BasePageAndSortRequest basePageAndSortRequest) {
        List<Product> products = productService.getAllProduct(basePageAndSortRequest);
        model.addAttribute("products", products);
        return "index";
    }

    @RequestMapping(value = {"/category"}, method = RequestMethod.POST)
    public String getAllProduct(HttpServletRequest request, Model model, @RequestParam("pageNumber") String pageNumber) {
        Integer pageNo = Integer.parseInt(pageNumber);
        BasePageAndSortRequest basePageAndSortRequest = new BasePageAndSortRequest();
        basePageAndSortRequest.setPageNumber(pageNo);
        List<Product> products = productService.getAllProduct(basePageAndSortRequest);
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/{id}")
    public String getById(HttpServletRequest request, Model model, @PathVariable("id") String id) {
        Product product = null;
        if (id != null && id.length() > 0) {
            product = productService.getProductById(id);
        }
        model.addAttribute("product", product);
        return "single-product";
    }

    @RequestMapping({ "/buyProduct" })
    public String listProductHandler(HttpServletRequest request, Model model,
                                     @RequestParam(value = "id", defaultValue = "") String id) {
        Product product = null;
        if (id != null && id.length() > 0) {
            product = productService.getProductById(id);
        }
        if (product != null) {
            Cart cartInfo = Utils.getCartInSession(request);
            cartInfo.addProduct(product, 1);
        }
        return "redirect:/shoppingCart";
    }
}
