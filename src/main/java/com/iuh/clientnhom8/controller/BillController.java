package com.iuh.clientnhom8.controller;

import com.iuh.clientnhom8.common.PaymentType;
import com.iuh.clientnhom8.entity.Bill;
import com.iuh.clientnhom8.entity.Cart;
import com.iuh.clientnhom8.entity.Customer;
import com.iuh.clientnhom8.entity.ProductSale;
import com.iuh.clientnhom8.model.ProductSaleId;
import com.iuh.clientnhom8.request.bill.CreateBillRequest;
import com.iuh.clientnhom8.response.LoginInfoResponse;
import com.iuh.clientnhom8.service.BillService;
import com.iuh.clientnhom8.service.CustomerService;
import com.iuh.clientnhom8.utils.CartUtils;
import com.iuh.clientnhom8.utils.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/bills")
public class BillController {
    private Logger logger = Logger.getLogger(getClass().getName());
    private final BillService billService;
    private final CustomerService customerService;

    public BillController(BillService billService,
                          CustomerService customerService) {
        this.billService = billService;
        this.customerService = customerService;
    }

    // POST: Save customer information.
    @RequestMapping(value = { "/createBill" }, method = RequestMethod.POST)
    public String shoppingCartCustomerSave(HttpServletRequest request, Model model, @ModelAttribute("customer") Customer customer) {
        // get data from session
        Cart cartInfo = CartUtils.getCartInSession(request);
        LoginInfoResponse loginInfoResponse = UserUtils.getUserInfo(request);

        Customer customerInfo = customerService.findById(loginInfoResponse.getUserId());
        customerInfo.setFirstName(customer.getFirstName());
        customerInfo.setLastName(customer.getLastName());
        customerInfo.setEmail(customer.getEmail());
        customerInfo.setPhoneNumber(customer.getPhoneNumber());
        customerInfo.setAddress(customer.getAddress());
        
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.DATE, 3);
        Date dateDelivery = c.getTime();

        List<ProductSaleId> productSaleIdList = new ArrayList<>();
        for (ProductSale productSale: cartInfo.getProductList()) {
            ProductSaleId productSaleId = ProductSaleId.builder()
                    .productId(productSale.getProduct().getId())
                    .quantity(productSale.getQuantity())
                    .build();
            productSaleIdList.add(productSaleId);
        }
        CreateBillRequest createBillRequest = CreateBillRequest.builder()
                .customer(customerInfo)
                .productSaleIdList(productSaleIdList)
                .dateDelivery(dateDelivery)
                .paymentType(PaymentType.CASH)
                .build();

        Bill bill = billService.createBill(createBillRequest);
        if (bill == null){
            return "error";
        }
        // Remove Cart from Session.
        CartUtils.removeCartInSession(request);

        // Store last cart.
        CartUtils.storeLastOrderedCartInSession(request, cartInfo);

        model.addAttribute("bill", bill);
        return "confirmation";
    }
}
