package com.iuh.clientnhom8.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoginInfoResponse {
    private String userName;
    private String userId;
    private String userType;
}
