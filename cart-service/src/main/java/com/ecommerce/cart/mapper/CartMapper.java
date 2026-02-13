package com.ecommerce.cart.mapper;

import com.ecommerce.cart.domain.Cart;
import com.ecommerce.cart.dto.CartItemDTO;
import com.ecommerce.cart.dto.CartResponseDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartMapper {

    public CartResponseDTO toDTO(Cart cart) {
        return new CartResponseDTO(
                cart.getId(),
                cart.getUserId(),
                cart.getStatus().name(),
                cart.getItems().stream()
                        .map(i -> new CartItemDTO(
                                i.getProductId(),
                                i.getPrice(),
                                i.getQuantity()
                        ))
                        .collect(Collectors.toList())
        );
    }
}

