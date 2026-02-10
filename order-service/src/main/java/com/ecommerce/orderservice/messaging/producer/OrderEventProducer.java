package com.ecommerce.orderservice.messaging.producer;

import com.ecommerce.orderservice.domain.event.OrderCreatedEvent;
import com.ecommerce.orderservice.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishOrderCreated(Order order) {
        OrderCreatedEvent event = new OrderCreatedEvent(
                order.getId(),
                order.getUserId(),
                order.getTotal()
        );
        kafkaTemplate.send("order-created-topic", event);
    }
}

