package com.iuh.clientnhom8.controller;

import com.iuh.clientnhom8.common.PaymentType;
import com.iuh.clientnhom8.entity.Bill;
import com.iuh.clientnhom8.entity.Cart;
import com.iuh.clientnhom8.entity.Customer;
import com.iuh.clientnhom8.entity.ProductSale;
import com.iuh.clientnhom8.model.ProductSaleId;
import com.iuh.clientnhom8.request.bill.CreateBillRequest;
import com.iuh.clientnhom8.service.BillService;
import com.iuh.clientnhom8.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    public BillController(BillService billService) {
        this.billService = billService;
    }

    // POST: Save customer information.
    @RequestMapping(value = { "/createBill" }, method = RequestMethod.POST)
    public String shoppingCartCustomerSave(HttpServletRequest request, Model model, @ModelAttribute("customer") Customer customer) {

        Cart cartInfo = Utils.getCartInSession(request);
        Customer customerInfo = Customer.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .build();
        
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
        Utils.removeCartInSession(request);

        // Store last cart.
        Utils.storeLastOrderedCartInSession(request, cartInfo);

        model.addAttribute("bill", bill);
        return "confirmation";
    }
}
