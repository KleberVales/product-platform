package com.ecommerce.usertest.service;

import com.ecommerce.usertest.dto.LoginRequestDTO;
import com.ecommerce.usertest.dto.LoginResponseDTO;
import com.ecommerce.usertest.dto.UserDTO;
import com.ecommerce.usertest.entity.User;
import com.ecommerce.usertest.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }


    public UserDTO findByEmail(String email) {

        User user = repo.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado com email: " + email)
                );

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPasswordHash(user.getPasswordHash());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());

        return userDTO;

    }




}
