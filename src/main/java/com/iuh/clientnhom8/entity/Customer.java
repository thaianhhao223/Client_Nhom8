package com.iuh.clientnhom8.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Customer implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String urlImage;
}
