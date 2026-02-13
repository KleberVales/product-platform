package com.ecommerce.payment.messaging.producer;

import com.ecommerce.payment.domain.enums.PaymentStatus;
import com.ecommerce.payment.domain.event.PaymentProcessedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentResultProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishPaymentResult(UUID orderId, PaymentStatus status) {

        PaymentProcessedEvent event = PaymentProcessedEvent.builder()
                .orderId(orderId)
                .status(status)
                .build();

        kafkaTemplate.send("payment-result-topic", event);
    }
}

