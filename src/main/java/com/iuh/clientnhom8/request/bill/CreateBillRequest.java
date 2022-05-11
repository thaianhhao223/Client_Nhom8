package com.iuh.clientnhom8.request.bill;

import com.iuh.clientnhom8.entity.Customer;
import com.iuh.clientnhom8.model.ProductSaleId;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CreateBillRequest {
    private Customer customer;
    private List<ProductSaleId> productSaleIdList;
    private Date dateDelivery;
    private Float percentDiscount;
    private String paymentType;
}
