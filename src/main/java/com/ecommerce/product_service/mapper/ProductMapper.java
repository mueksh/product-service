package com.ecommerce.product_service.mapper;

import com.ecommerce.product_service.dao.ProductRequest;
import com.ecommerce.product_service.dao.ProductResponse;
import com.ecommerce.product_service.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public static Product toProductEntity(ProductRequest req) {
        return Product.builder()
                .name(req.getName())
                .description(req.getDescription())
                .price(req.getPrice())
                .brand(req.getBrand())
                .categoryId(req.getCategoryId())
                .build();
    }

    public static ProductResponse toProductResponse(Product p) {
        return ProductResponse.builder()
                .id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .brand(p.getBrand())
                .categoryId(p.getCategoryId())
                .build();
    }

    public static List<ProductResponse> toProductResponseList(List<Product> productList){

        return productList.stream()
                .filter(Objects::nonNull)
                .map(ProductMapper::toProductResponse)
                .collect(Collectors.toList());

    }
}