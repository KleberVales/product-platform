package com.ecommerce.orderservice.domain.event;

public record PaymentResultEvent(
        Long orderId,
        boolean success
) {}

