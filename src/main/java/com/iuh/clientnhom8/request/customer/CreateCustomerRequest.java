package com.iuh.clientnhom8.request.customer;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class CreateCustomerRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String urlImage;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private Integer status;
}
