package com.iuh.clientnhom8.model.customer;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Generated
public class UpdateRequest implements Serializable {
    private static final long serialVersionUID = 7581709400470270054L;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private MultipartFile urlImage;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private Integer status;

    public String getName(){
        return lastName + " " +firstName;
    }
}
