package com.iuh.clientnhom8.request.salestaff;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UpdateStatusSaleStaffRequest {
    private String id;
    private Integer status;
}
