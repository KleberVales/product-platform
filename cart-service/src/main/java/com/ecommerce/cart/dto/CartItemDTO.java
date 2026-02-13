package com.ecommerce.cart.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CartItemDTO(
        UUID productId,
        BigDecimal price,
        int quantity
) {}

