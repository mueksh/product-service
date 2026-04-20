package com.ecommerce.product_service.dao;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String brand;
    private String categoryId;
}
