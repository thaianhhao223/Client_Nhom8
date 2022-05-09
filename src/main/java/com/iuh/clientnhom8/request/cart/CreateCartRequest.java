package com.iuh.clientnhom8.request.cart;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CreateCartRequest {
    private String customerId;
    private List<String> productIdList;
}
