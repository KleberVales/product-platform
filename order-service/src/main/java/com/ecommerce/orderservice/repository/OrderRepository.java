package com.ecommerce.orderservice.repository;

import com.ecommerce.orderservice.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

