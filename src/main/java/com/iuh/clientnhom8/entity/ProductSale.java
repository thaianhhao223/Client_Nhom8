package com.iuh.clientnhom8.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class ProductSale {
	private Product product;
    private Integer quantity;

    public ProductSale() {
        this.quantity = 0;
    }

    public double getTotalAmount() {
        return this.product.getAmount() * this.quantity;
    }

    public String getAmount(){
        return String.format("%.0f", getTotalAmount());
    }
}
