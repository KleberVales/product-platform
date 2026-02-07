package com.ecommerce.usertest.dto;

import com.ecommerce.usertest.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String username;
    private String email;
    private String passwordHash;
    private Role role;

}
