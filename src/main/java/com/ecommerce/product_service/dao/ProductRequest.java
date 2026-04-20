package com.ecommerce.product_service.dao;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @Size(max = 1000)
    private String description;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal price;

    @NotBlank
    private String brand;

    @NotBlank
    private String categoryId;
}
