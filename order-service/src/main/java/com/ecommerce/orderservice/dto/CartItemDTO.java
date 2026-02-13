package com.ecommerce.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class CartItemDTO {

    private UUID productId;
    private BigDecimal price;
    private Integer quantity;

    // getters e setters
}

