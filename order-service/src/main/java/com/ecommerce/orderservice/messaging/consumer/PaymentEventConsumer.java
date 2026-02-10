package com.ecommerce.orderservice.messaging.consumer;

import com.ecommerce.orderservice.domain.event.PaymentResultEvent;
import com.ecommerce.orderservice.domain.model.Order;
import com.ecommerce.orderservice.domain.model.OrderStatus;
import com.ecommerce.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentEventConsumer {

    private final OrderService orderService;

    @KafkaListener(topics = "payment-result-topic")
    public void consume(PaymentResultEvent event) {
        Order order = orderService.findById(event.orderId());
        order.setStatus(event.success() ? OrderStatus.PAID : OrderStatus.CANCELED);
    }
}

