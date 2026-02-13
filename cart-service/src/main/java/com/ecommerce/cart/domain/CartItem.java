package com.ecommerce.cart.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
public class CartItem {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID productId;
    private BigDecimal price;
    private int quantity;

    /** Obrigatório para JPA */
    protected CartItem() {
    }

    /** Construtor de domínio */
    public CartItem(UUID productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartItem(UUID productId, int quantity, BigDecimal price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    /** Regra de negócio */
    public void incrementQuantity(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Quantity increment must be greater than zero");
        }
        this.quantity += amount;
    }

    // getters
}

