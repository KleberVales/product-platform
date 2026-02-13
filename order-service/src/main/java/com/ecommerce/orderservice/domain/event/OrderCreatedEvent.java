package com.ecommerce.orderservice.domain.event;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderCreatedEvent(
        Long orderId,
        UUID userId,
        BigDecimal total
) {}

