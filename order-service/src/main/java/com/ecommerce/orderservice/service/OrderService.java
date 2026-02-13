package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.client.CartClient;
import com.ecommerce.orderservice.domain.exception.OrderNotFoundException;
import com.ecommerce.orderservice.domain.model.Order;
import com.ecommerce.orderservice.domain.model.OrderItem;
import com.ecommerce.orderservice.domain.model.OrderStatus;
import com.ecommerce.orderservice.dto.CartItemDTO;
import com.ecommerce.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CartClient cartClient;


        

        public Order createOrder(UUID userId) {

            List<CartItemDTO> cartItems = cartClient.getCartItems(userId);

            if (cartItems.isEmpty()) {
                throw new RuntimeException("Cart is empty");
            }

            Order order = new Order();
            order.setUserId(userId);

            List<OrderItem> orderItems = cartItems.stream()
                    .map(item -> new OrderItem(
                            item.getProductId(),
                            item.getPrice(),
                            item.getQuantity()
                    ))
                    .toList();

            order.setItems(orderItems);

            order.calculateTotal();

            return order;

    }


    public Order findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }
}

