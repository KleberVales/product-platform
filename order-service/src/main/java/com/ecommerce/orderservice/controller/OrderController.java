package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.domain.model.Order;
import com.ecommerce.orderservice.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CheckoutService checkoutService;

    @PostMapping("/checkout/{userId}")
    public ResponseEntity<Order> checkout(@PathVariable Long userId) {
        Order order = checkoutService.checkout(userId);
        return ResponseEntity.ok(order);
    }
}

