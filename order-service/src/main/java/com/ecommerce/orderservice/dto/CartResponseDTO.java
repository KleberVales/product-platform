package com.ecommerce.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CartResponseDTO {

    private UUID cartId;
    private UUID userId;
    private String status;
    private List<CartItemDTO> items;

    // getters e setters
}

