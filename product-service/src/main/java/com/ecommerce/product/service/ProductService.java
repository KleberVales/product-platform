package com.ecommerce.product.service;

import com.ecommerce.product.dto.request.CreateProductRequest;
import com.ecommerce.product.dto.request.UpdateProductRequest;
import com.ecommerce.product.dto.response.ProductResponse;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.exception.ResourceNotFoundException;
import com.ecommerce.product.mapper.ProductMapper;
import com.ecommerce.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductEventService eventService;

    public ProductService(ProductRepository repository,
                          ProductEventService eventService) {
        this.repository = repository;
        this.eventService = eventService;
    }

    public ProductResponse create(CreateProductRequest request) {

        Product product = ProductMapper.toEntity(request);
        repository.save(product);
        eventService.publishCreated(product);
        
        return ProductMapper.toResponse(product);
    }

    public ProductResponse update(String id, UpdateProductRequest request) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        ProductMapper.updateEntity(product, request);
        repository.save(product);
        eventService.publishUpdated(product);
        return ProductMapper.toResponse(product);
    }

    public void disable(String id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        product.setActive(false);
        repository.save(product);
        eventService.publishDisabled(product);
    }

    public ProductResponse findById(String id) {
        return repository.findById(id)
                .map(ProductMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
    }
}

