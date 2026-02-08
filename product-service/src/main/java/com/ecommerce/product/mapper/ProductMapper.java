package com.ecommerce.product.mapper;

import com.ecommerce.product.dto.request.CreateProductRequest;
import com.ecommerce.product.dto.request.UpdateProductRequest;
import com.ecommerce.product.dto.response.ProductResponse;
import com.ecommerce.product.entity.Product;

public class ProductMapper {

    public static Product toEntity(CreateProductRequest request) {
        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        return product;
    }

    public static void updateEntity(Product product, UpdateProductRequest request) {
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
    }

    public static ProductResponse toResponse(Product product) {

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.isActive()
        );
    }
}

