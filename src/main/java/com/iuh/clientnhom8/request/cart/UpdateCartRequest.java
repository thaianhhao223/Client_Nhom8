package com.iuh.clientnhom8.request.cart;

import com.iuh.clientnhom8.model.ProductSaleId;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UpdateCartRequest {
    private String cartId;
    private String customerId;
    private List<ProductSaleId> productSale;
}
