package com.ecommerce.authtest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String username;
    private String email;
    private String passwordHash;
    private String role;


}
