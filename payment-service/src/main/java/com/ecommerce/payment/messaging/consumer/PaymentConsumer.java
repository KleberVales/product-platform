package com.ecommerce.payment.messaging.consumer;

import com.ecommerce.payment.domain.event.OrderCreatedEvent;
import com.ecommerce.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentConsumer {

    private final PaymentService paymentService;

    @KafkaListener(
            topics = "order-created-topic",
            groupId = "payment-service",
            containerFactory = "kafkaListenerContainerFactory"  
    )
    public void consume(OrderCreatedEvent event) {

        log.info("💰 Received OrderCreatedEvent: {}", event);

        paymentService.processPayment(event);
    }
}

