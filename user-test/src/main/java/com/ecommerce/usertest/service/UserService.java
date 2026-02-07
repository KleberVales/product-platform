package com.ecommerce.usertest.service;

import com.ecommerce.usertest.dto.LoginRequestDTO;
import com.ecommerce.usertest.dto.LoginResponseDTO;
import com.ecommerce.usertest.entity.User;
import com.ecommerce.usertest.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }


    public LoginResponseDTO findByEmail(LoginRequestDTO dto) {

        User user = repo.findByEmail(dto.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado com email: " + dto.getEmail())
                );

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setPasswordHash(user.getPasswordHash());

        return loginResponseDTO;
    }




}
