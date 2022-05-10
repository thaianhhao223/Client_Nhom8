package com.iuh.clientnhom8.request.bill;

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
    private String customerId;
    private String saleStaffId;
    private List<ProductSaleId> productSaleIdList;
    private Date dateDelivery;
    private Float percentDiscount;
    private String paymentType;
}
