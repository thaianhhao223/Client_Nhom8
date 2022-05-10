package com.iuh.clientnhom8.model;

import com.iuh.clientnhom8.entity.Product;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductSale {
    private Product product;
    private Integer quantity;
}
