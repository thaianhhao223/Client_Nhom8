package com.iuh.clientnhom8.request;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RegisterRequest {
    private String userName;
    private String passWord;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
}
