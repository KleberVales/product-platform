package com.ecommerce.cart.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Cart {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID userId;

    @Enumerated(EnumType.STRING)
    private CartStatus status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();


    public Cart(UUID userId) {
        this.userId = userId;
    }

    public Cart(UUID userId, CartStatus status) {
        this.userId = userId;
        this.status = status;
    }

    public void addItem(UUID productId, int quantity, BigDecimal price) {

        CartItem item = items.stream()
                .filter(i -> i.getProductId().equals(productId))
                .findFirst()
                .orElse(null);

        if (item == null) {
            items.add(new CartItem(productId, quantity, price));
        } else {
            item.incrementQuantity(quantity);
        }
    }

}

