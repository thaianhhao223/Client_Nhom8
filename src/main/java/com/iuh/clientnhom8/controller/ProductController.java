package com.iuh.clientnhom8.controller;

import com.iuh.clientnhom8.base.request.BasePageAndSortRequest;
import com.iuh.clientnhom8.entity.Cart;
import com.iuh.clientnhom8.entity.Product;

import com.iuh.clientnhom8.response.ProductListResponse;
import com.iuh.clientnhom8.service.ProductService;
import com.iuh.clientnhom8.utils.CartUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @RequestMapping(value = {"/category", "/"}, method = RequestMethod.GET)
    public String getAllProduct(HttpServletRequest request, Model model) {
        int page = 0; //default page number is 0
        int size = 12; //default page size is 12
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            page = Integer.parseInt(request.getParameter("size"));
        }
        BasePageAndSortRequest pageRequest = new BasePageAndSortRequest();
        pageRequest.setPageSize(size);
        pageRequest.setPageNumber(page);
        ProductListResponse products = productService.getProductPage(pageRequest);
        model.addAttribute("products", products);
        return "category";
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
            Cart cartInfo = CartUtils.getCartInSession(request);
            cartInfo.addProduct(product, 1);
        }
        return "redirect:/shoppingCart";
    }
}
