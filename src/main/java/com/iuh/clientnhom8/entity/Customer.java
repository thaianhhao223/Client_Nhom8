package com.iuh.clientnhom8.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Customer implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String urlImage;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private Date createdDate;
    private Integer status;

    public String getName(){
        return lastName + " " +firstName;
    }
}
