package com.ecommerce.cart.controller;

import com.ecommerce.cart.domain.Cart;
import com.ecommerce.cart.dto.AddItemRequestDTO;
import com.ecommerce.cart.dto.CartResponseDTO;
import com.ecommerce.cart.mapper.CartMapper;
import com.ecommerce.cart.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService service;
    private final CartMapper mapper;

    public CartController(CartService service, CartMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartResponseDTO> getCart(@PathVariable UUID userId) {

        Cart cart = service.getCartByUser(userId);

        CartResponseDTO response = mapper.toDTO(cart);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userId}/items")
    public CartResponseDTO addItem(
            @PathVariable UUID userId,
            @RequestBody AddItemRequestDTO request
    ) {
        Cart cart = service.addItem(userId, request);
        return mapper.toDTO(cart);
    }



}

