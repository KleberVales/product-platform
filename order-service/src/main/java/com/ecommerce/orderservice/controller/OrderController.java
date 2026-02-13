package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.domain.model.Order;
import com.ecommerce.orderservice.service.CheckoutService;
import com.ecommerce.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CheckoutService checkoutService;
    private final OrderService orderService;

    @PostMapping("/checkout/{userId}")
    public ResponseEntity<Order> checkout(@PathVariable UUID userId) {
        Order order = checkoutService.checkout(userId);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Order> createOrder(@PathVariable UUID userId) {

        Order order = orderService.createOrder(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);

    }




}

