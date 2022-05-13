package com.iuh.clientnhom8.controller;

import com.iuh.clientnhom8.base.request.BasePageAndSortRequest;
import com.iuh.clientnhom8.entity.Customer;
import com.iuh.clientnhom8.entity.Product;
import com.iuh.clientnhom8.entity.ProductBrand;
import com.iuh.clientnhom8.entity.ProductType;
import com.iuh.clientnhom8.model.customer.CustomerRequest;
import com.iuh.clientnhom8.request.customer.CreateCustomerRequest;
import com.iuh.clientnhom8.request.customer.UpdateCustomerRequest;
import com.iuh.clientnhom8.service.*;

import com.lowagie.text.DocumentException;
import com.iuh.clientnhom8.utils.MappingUtils;
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
    private UploadFileService uploadFileService;

    public AdminController(AdminService adminService, CustomerService customerService, ProductService productService, ProductTypeService productTypeService, ProductBrandService productBrandService, BillService billService, UploadFileService uploadFileService) {
        this.adminService = adminService;
        this.customerService = customerService;
        this.productService = productService;
        this.productTypeService = productTypeService;
        this.productBrandService = productBrandService;
        this.billService = billService;
        this.uploadFileService = uploadFileService;
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
    @GetMapping("/type/export/exel")
    public void exportToExelForProductType(HttpServletResponse response) throws IOException {
        List<ProductType> productTypes = productTypeService.getAllProductType();
        ProductTypeExelExporter exporter = new ProductTypeExelExporter();
        exporter.export(productTypes, response);
    }

    @GetMapping("/brand/export/exel")
    public void exportToExelForBrand(HttpServletResponse response) throws IOException {
        List<ProductBrand> productBrands = productBrandService.getAllProductBrand();
        ProductBrandExelExporter exporter = new ProductBrandExelExporter();
        exporter.export(productBrands, response);
    }
    @GetMapping("/product/export/exel")
    public void exportToExelForProduct(HttpServletResponse response) throws IOException {
        List<Product> products = productService.getAllProduct(new BasePageAndSortRequest());
        ProductExelExporter exporter = new ProductExelExporter();
        exporter.export(products, response);
    }
    @GetMapping("/customers/export/pdf")
    public void exportToPdfForCustomer(HttpServletResponse response) throws IOException, DocumentException {
        List<Customer> customerList = customerService.getAllCustomer();
        System.out.println(" customerList" + customerList);
        CustomerPdfExporter exporter = new CustomerPdfExporter();
        exporter.export(customerList, response);
    }

    @GetMapping("/customers/create")
    public String createCustomer(Model model) throws IOException {
        CustomerRequest customer = new CustomerRequest();

        model.addAttribute("customer",customer);
        return "customer-admin-create";

    }

    @PostMapping("/customers/created")
    public String createdCustomer(@ModelAttribute("customer") CustomerRequest customer, Model model) throws IOException {
//        String urlImage = uploadFileService.uploadFile(customer.getUrlImage());
//        System.out.println(urlImage);
        System.out.println(customer.toString());
        CreateCustomerRequest createCustomerRequest = MappingUtils.mapObject(customer, CreateCustomerRequest.class);
        customerService.createCustomer(createCustomerRequest);
        System.out.println(createCustomerRequest.toString());
        model.addAttribute("customers", customerService.getAllCustomer());
        return "customer-admin";

    }

    @GetMapping("/customers/update/{id}")
    public String updateCustomer(@PathVariable("id") String id, Model model) throws IOException {
        CustomerRequest customerRequest = new CustomerRequest();
        Customer customer = customerService.findById(id);
        customerRequest = MappingUtils.mapObject(customer, CustomerRequest.class);
        customerRequest.setId(id);
        System.out.println(customerRequest.toString());
        model.addAttribute("customer",customerRequest);
        return "customer-admin-update";
    }

    @PostMapping("/customers/update/updated/{id}")
    public String updateCustomer(@PathVariable("id") String id, @ModelAttribute("customer") CustomerRequest customer, Model model) throws IOException {
        UpdateCustomerRequest updateCustomerRequest = MappingUtils.mapObject(customer, UpdateCustomerRequest.class);
        updateCustomerRequest.setId(id);
        System.out.println(updateCustomerRequest.toString());
        customerService.updateCustomer(updateCustomerRequest);
        model.addAttribute("customers", customerService.getAllCustomer());
        return "customer-admin";
    }
    @GetMapping("/product/create")
    public String createProduct(HttpServletResponse response) throws IOException {
        return "product-admin-create";

    }
    @GetMapping("/product/update")
    public String updateProduct(HttpServletResponse response) throws IOException {
        return "product-admin-update";

    }
    @GetMapping("/bill/create")
    public String createBill(HttpServletResponse response) throws IOException {
        return "bill-admin-create";
    }

    @GetMapping("/customers/customer-order")
    public String getCustomerById(HttpServletResponse response) throws IOException {
        return "customer-order-admin";
    }

}
