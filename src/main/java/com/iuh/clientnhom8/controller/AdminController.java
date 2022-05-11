package com.iuh.clientnhom8.controller;

import com.iuh.clientnhom8.base.request.BasePageAndSortRequest;
import com.iuh.clientnhom8.entity.Customer;
import com.iuh.clientnhom8.entity.Product;
import com.iuh.clientnhom8.entity.ProductBrand;
import com.iuh.clientnhom8.entity.ProductType;
import com.iuh.clientnhom8.request.customer.CreateCustomerRequest;
import com.iuh.clientnhom8.service.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private Logger logger = Logger.getLogger(getClass().getName());

    private AdminService adminService;
    private CustomerService customerService;
    private ProductService productService;
    private ProductTypeService productTypeService;
    private ProductBrandService productBrandService;
    private BillService billService;

    public AdminController(AdminService adminService, CustomerService customerService, ProductService productService, ProductTypeService productTypeService, ProductBrandService productBrandService, BillService billService) {
        this.adminService = adminService;
        this.customerService = customerService;
        this.productService = productService;
        this.productTypeService = productTypeService;
        this.productBrandService = productBrandService;
        this.billService = billService;
    }

    @GetMapping("/home")
    public String homeAdmin(){
        return "dashboard-admin";
    }

    @GetMapping("/account")
    public String accountAdmin(){
        return "account-admin";
    }

    @GetMapping("/customers")
    public String getAllCustomer(Model model) {

//        model.addAttribute("customers", adminService.getAllCustomer());
        model.addAttribute("customers", customerService.getAllCustomer());
        return "customer-admin";
    }

    @GetMapping("/type")
    public String typeAdmin(Model model){
        model.addAttribute("producttype", productTypeService.getAllProductType());
        return "type-admin";
    }

    @GetMapping("/brand")
    public String brandAdmin(Model model){
        model.addAttribute("productbrand", productBrandService.getAllProductBrand());
        return "brand-admin";
    }

    @GetMapping("/product")
    public String productAdmin(Model model){
        model.addAttribute("products", productService.getAllProduct(new BasePageAndSortRequest()));
        return "product-admin";
    }

    @GetMapping("/billing")
    public String billAdmin(Model model){
        model.addAttribute("bills", billService.getAllBill());
        return "billing-admin";
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

//    @GetMapping("/{id}")
//    public String getProductById(Model model, @PathVariable("id") String id) {
//        model.addAttribute("product", adminService.getProductById(id));
//        return "single-product";
//    }

    @GetMapping("/customers/export/csv")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        List<Customer> customerList = customerService.getAllCustomer();
        CustomerCsvExporter exporter = new CustomerCsvExporter();
        exporter.export(customerList, response);
    }
    @GetMapping("/type/export/csv")
    public void exportToCSVForType(HttpServletResponse response) throws IOException {
        List<ProductType> productTypes = productTypeService.getAllProductType();
        ProductTypeCsvExporter exporter = new ProductTypeCsvExporter();
        exporter.export(productTypes, response);
    }
    @GetMapping("/brand/export/csv")
    public void exportToCSVForBrand(HttpServletResponse response) throws IOException {
        List<ProductBrand> productBrands = productBrandService.getAllProductBrand();
        ProductBrandCsvExporter exporter = new ProductBrandCsvExporter();
        exporter.export(productBrands, response);
    }
    @GetMapping("/product/export/csv")
    public void exportToCSVForProduct(HttpServletResponse response) throws IOException {
        List<Product> products = productService.getAllProduct(new BasePageAndSortRequest());
        ProductCsvExporter exporter = new ProductCsvExporter();
        exporter.export(products, response);
    }
    @GetMapping("/customers/export/exel")
    public void exportToExelForCustomer(HttpServletResponse response) throws IOException {
        List<Customer> customerList = customerService.getAllCustomer();
        CustomerExelExporter exporter = new CustomerExelExporter();
        exporter.export(customerList, response);
    }


}
