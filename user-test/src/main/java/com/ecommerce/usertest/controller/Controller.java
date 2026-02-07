package com.ecommerce.usertest.controller;

import com.ecommerce.usertest.dto.LoginRequestDTO;
import com.ecommerce.usertest.dto.LoginResponseDTO;
import com.ecommerce.usertest.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class Controller {

    private UserService service;

    public Controller(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO dto) {





        return service.findByEmail(dto);
    }
}
