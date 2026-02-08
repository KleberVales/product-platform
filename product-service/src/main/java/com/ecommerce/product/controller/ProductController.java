package com.ecommerce.product.controller;

import com.ecommerce.product.dto.request.CreateProductRequest;
import com.ecommerce.product.dto.request.UpdateProductRequest;
import com.ecommerce.product.dto.response.ProductResponse;
import com.ecommerce.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@RequestBody CreateProductRequest request) {

        return service.create(request);
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable String id,
                                  @RequestBody UpdateProductRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disable(@PathVariable String id) {
        service.disable(id);
    }

    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable String id) {
        return service.findById(id);
    }
}

