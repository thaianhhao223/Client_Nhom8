package com.iuh.clientnhom8.entity;

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
public class Cart {
	private String id;
    private Customer customer;
    private List<ProductSale> productList;
    private Long totalPrice;
    private Float percentDiscount;
}
