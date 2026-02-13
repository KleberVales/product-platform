package com.ecommerce.orderservice.client;

import com.ecommerce.orderservice.domain.model.Order;
import com.ecommerce.orderservice.domain.model.OrderItem;
import com.ecommerce.orderservice.dto.CartItemDTO;
import com.ecommerce.orderservice.dto.CartResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartClient {

    private final WebClient webClient;

    public List<CartItemDTO> getCartItems(UUID userId) {

        CartResponseDTO cart = webClient.get()
                .uri("http://localhost:8081/api/cart/{userId}", userId)
                .retrieve()
                .bodyToMono(CartResponseDTO.class)
                .block();

        if (cart == null || cart.getItems() == null) {
            return Collections.emptyList();
        }

        return cart.getItems();
    }

}
