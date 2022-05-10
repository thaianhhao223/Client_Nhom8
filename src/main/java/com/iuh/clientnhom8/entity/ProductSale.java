package com.iuh.clientnhom8.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public double getAmount() {
        return this.product.getAmount() * this.quantity;
    }
}
