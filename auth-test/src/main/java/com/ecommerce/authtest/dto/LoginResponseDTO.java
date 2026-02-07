package com.ecommerce.authtest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {

    private String token;
    private String type = "Bearer";
    private long expiresIn;

}
