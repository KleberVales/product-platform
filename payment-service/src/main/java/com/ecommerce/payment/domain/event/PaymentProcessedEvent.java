package com.ecommerce.payment.domain.event;

import com.ecommerce.payment.domain.enums.PaymentStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentProcessedEvent {

    private UUID orderId;
    private PaymentStatus status;
}

