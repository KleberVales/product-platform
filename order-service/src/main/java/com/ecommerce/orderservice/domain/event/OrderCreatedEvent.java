package com.ecommerce.orderservice.domain.event;

import java.math.BigDecimal;

public record OrderCreatedEvent(
        Long orderId,
        Long userId,
        BigDecimal total
) {}

