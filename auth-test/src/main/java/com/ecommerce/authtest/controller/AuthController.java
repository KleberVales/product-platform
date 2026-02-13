package com.ecommerce.authtest.controller;

import com.ecommerce.authtest.client.UserClient;
import com.ecommerce.authtest.dto.LoginRequestDTO;
import com.ecommerce.authtest.dto.LoginResponseDTO;
import com.ecommerce.authtest.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO dto, HttpServletRequest request
    ) {

        return ResponseEntity.ok(service.login(dto));
    }
}

