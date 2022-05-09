package com.iuh.clientnhom8.request.customer;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UpdateStatusCustomerRequest {
    private String id;
    private Integer status;
}
