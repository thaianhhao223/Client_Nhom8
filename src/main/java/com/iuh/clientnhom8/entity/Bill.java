package com.iuh.clientnhom8.entity;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Bill {
	private String id;
    private Customer customer;
    private List<ProductSale> productList;
    private Date dateSale;
    private Date dateDelivery;
    private Double totalPrice;
    private Float percentDiscount;
    private String paymentType;
    private String status;

    public String getTotal(){
        return String.format("%.0f", totalPrice);
    }
}
