package com.ecommerce.product.event.publisher;

import com.ecommerce.product.entity.Product;
import com.ecommerce.product.event.model.*;
import com.ecommerce.product.event.topic.ProductTopics;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ProductEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishCreated(Product product) {

        kafkaTemplate.send(ProductTopics.PRODUCT_CREATED,
                new ProductCreatedEvent(product));

    }

    public void publishUpdated(Product product) {
        kafkaTemplate.send(ProductTopics.PRODUCT_UPDATED,
                new ProductUpdatedEvent(product));
    }

    public void publishDisabled(Product product) {
        kafkaTemplate.send(ProductTopics.PRODUCT_DISABLED,
                new ProductDisabledEvent(product.getId()));
    }
}
