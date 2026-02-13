package com.ecommerce.cart.service;

import com.ecommerce.cart.domain.Cart;
import com.ecommerce.cart.domain.CartStatus;
import com.ecommerce.cart.dto.AddItemRequestDTO;
import com.ecommerce.cart.exception.CartNotFoundException;
import com.ecommerce.cart.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartService {

    private final CartRepository repository;

    public CartService(CartRepository repository) {
        this.repository = repository;
    }

    public Cart getCartByUser(UUID userId) {

        return repository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart(userId, CartStatus.ACTIVE);
                    return repository.save(newCart);
                });
    }



    public Cart addItem(UUID userId, AddItemRequestDTO dto) {

        Cart cart = repository.findByUserId(userId)
                .orElseGet(() -> repository.save(new Cart(userId)));

        cart.addItem(dto.getProductId(), dto.getQuantity(), dto.getPrice());

        return repository.save(cart);
    }

    public Cart createCart(UUID userId) {
        Cart cart = Cart.builder()
                .userId(userId)
                .status(CartStatus.ACTIVE)
                .build();
        return repository.save(cart);
    }
}

