package com.ecommerce.authtest.client;

import com.ecommerce.authtest.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "user-test", url = "${user.service.url}")
public interface UserClient {

    @GetMapping("/users/email/{email}")
    UserDTO findByEmail(@PathVariable String email);

}
