package com.ecommerce.usertest.dto;

public class LoginResponseDTO {

    private String passwordHash;

    public String getPasswordHash() {

        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

}
