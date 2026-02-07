package com.ecommerce.authtest.service;

import com.ecommerce.authtest.client.UserClient;
import com.ecommerce.authtest.dto.LoginRequestDTO;
import com.ecommerce.authtest.dto.LoginResponseDTO;
import com.ecommerce.authtest.dto.UserDTO;
import com.ecommerce.authtest.security.JwtTokenProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserClient userClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(UserClient userClient, PasswordEncoder passwordEncoder,  JwtTokenProvider jwtTokenProvider) {
        this.userClient = userClient;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {

        UserDTO userDTO = userClient.findByEmail(dto.getEmail());

        if (!passwordEncoder.matches(dto.getPassword(), userDTO.getPasswordHash())) {
            throw new BadCredentialsException("Credenciais inválidas");
        }

        String token = jwtTokenProvider.generateToken(
                userDTO.getEmail(),
                userDTO.getRole()
        );

        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        response.setExpiresIn(jwtTokenProvider.getExpiration());

        return response;
    }
}

