package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.domain.exception.OrderNotFoundException;
import com.ecommerce.orderservice.domain.model.Order;
import com.ecommerce.orderservice.domain.model.OrderItem;
import com.ecommerce.orderservice.domain.model.OrderStatus;
import com.ecommerce.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public Order createOrder(Long userId, List<OrderItem> items) {
        Order order = new Order();
        order.setUserId(userId);
        order.setItems(items);
        order.setStatus(OrderStatus.CREATED);
        order.calculateTotal();
        return repository.save(order);
    }

    public Order findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }
}

