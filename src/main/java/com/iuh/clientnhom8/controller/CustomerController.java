package com.iuh.clientnhom8.controller;

import com.iuh.clientnhom8.request.customer.CreateCustomerRequest;
import com.iuh.clientnhom8.service.AdminService;
import com.iuh.clientnhom8.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
@RequestMapping("customers")
public class CustomerController {
	private Logger logger = Logger.getLogger(getClass().getName());
	private final CustomerService customerService;


	public CustomerController(CustomerService customerService, AdminService adminService) {
		this.customerService = customerService;

	}

	@GetMapping("/list")
	public String getAllCustomer(Model model) {
		model.addAttribute("customers", customerService.getAllCustomer());

		return "customer-list";
	}
	
	@GetMapping("/getById={id}")
	public String getById(Model model, @PathVariable("id") String id) {
		model.addAttribute("customer", customerService.findById(id));

		return "customer-detail";
	}
	
	@PostMapping("/create")
	public String createCustomer(@Valid @ModelAttribute("customer") CreateCustomerRequest request, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "customer-form";
		customerService.createCustomer(request);

		return "redirect:list";
	}

}
