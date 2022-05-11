package com.iuh.clientnhom8.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Account {
    private String userName;
    private String passWord;
    private String userId;
    private String userType;
}
