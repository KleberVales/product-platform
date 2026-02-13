package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.client.CartClient;
import com.ecommerce.orderservice.domain.model.Order;
import com.ecommerce.orderservice.domain.model.OrderItem;
import com.ecommerce.orderservice.dto.CartItemDTO;
import com.ecommerce.orderservice.messaging.producer.OrderEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final CartClient cartClient;
    private final OrderService orderService;
    private final OrderEventProducer eventProducer;

    public Order checkout(UUID userId) {
        List<CartItemDTO> items = cartClient.getCartItems(userId);
        Order order = orderService.createOrder(userId);
        eventProducer.publishOrderCreated(order);
        return order;
    }
}

