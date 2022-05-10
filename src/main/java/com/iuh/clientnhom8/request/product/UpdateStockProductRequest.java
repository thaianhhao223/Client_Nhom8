package com.iuh.clientnhom8.request.product;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UpdateStockProductRequest {
    private String productId;
    private Integer stockHadSolve; //Số lượng tồn

}
