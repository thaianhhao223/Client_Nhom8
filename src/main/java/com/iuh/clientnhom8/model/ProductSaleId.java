package com.iuh.clientnhom8.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductSaleId {
    private String productId;
    private Integer quantity;
}
