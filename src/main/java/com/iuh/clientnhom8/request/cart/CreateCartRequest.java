package com.iuh.clientnhom8.request.cart;

import lombok.*;

import javax.persistence.ElementCollection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CreateCartRequest {
    private String customerId;
    @ElementCollection
    private List<String> productIdList;
}
