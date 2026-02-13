package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.client.CartClient;
import com.ecommerce.orderservice.domain.exception.OrderNotFoundException;
import com.ecommerce.orderservice.domain.model.Order;
import com.ecommerce.orderservice.domain.model.OrderItem;
import com.ecommerce.orderservice.domain.model.OrderStatus;
import com.ecommerce.orderservice.dto.CartItemDTO;
import com.ecommerce.orderservice.messaging.producer.OrderEventProducer;
import com.ecommerce.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository repository;
    private final CartClient cartClient;
    private final OrderEventProducer orderEventProducer;


    public Order createOrder(UUID userId) {
        log.info("Iniciando criação de pedido para usuário: {}", userId);

        List<CartItemDTO> cartItems = cartClient.getCartItems(userId);

        if (cartItems.isEmpty()) {
            log.warn("Tentativa de criar pedido com carrinho vazio para usuário: {}", userId);
            throw new RuntimeException("Cart is empty");
        }

        // Cria o pedido
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

        // Salva o pedido no banco de dados
        log.info("Salvando pedido para usuário: {}. Total: {}", userId, order.getTotal());
        Order savedOrder = repository.save(order);

        // Publica o evento no Kafka
        try {
            log.info("Publicando evento de pedido criado para o pedido: {}", savedOrder.getId());
            orderEventProducer.publishOrderCreated(savedOrder);
            log.info("Evento publicado com sucesso para o pedido: {}", savedOrder.getId());
        } catch (Exception e) {
            log.error("Erro ao publicar evento para o pedido: {}. Erro: {}", savedOrder.getId(), e.getMessage(), e);
            // Aqui você pode decidir se lança a exceção ou não
            // throw new RuntimeException("Erro ao publicar evento no Kafka", e);
        }

        return savedOrder;
    }



    public Order findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }
}

