package com.iuh.clientnhom8.request.salestaff;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UpdateSaleStaffRequest {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String urlImage;
}
