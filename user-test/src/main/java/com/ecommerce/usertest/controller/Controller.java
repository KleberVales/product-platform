package com.ecommerce.usertest.controller;

import com.ecommerce.usertest.dto.LoginRequestDTO;
import com.ecommerce.usertest.dto.LoginResponseDTO;
import com.ecommerce.usertest.dto.UserDTO;
import com.ecommerce.usertest.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class Controller {

    private UserService service;

    public Controller(UserService service) {
        this.service = service;
    }

    @GetMapping("/email/{email}")
    public UserDTO findByEmail(@PathVariable String email) {

        UserDTO userDTO = service.findByEmail(email);

        return userDTO;

    }


}
