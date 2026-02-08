package com.ecommerce.product.service;

import com.ecommerce.product.entity.Product;
import com.ecommerce.product.event.publisher.ProductEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class ProductEventService {

    private final ProductEventPublisher publisher;

    public ProductEventService(ProductEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publishCreated(Product product) {

        publisher.publishCreated(product);

    }

    public void publishUpdated(Product product) {
        publisher.publishUpdated(product);
    }

    public void publishDisabled(Product product) {
        publisher.publishDisabled(product);
    }
}

