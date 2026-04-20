package com.ecommerce.product_service.service;

import com.ecommerce.product_service.dao.ProductRequest;
import com.ecommerce.product_service.dao.ProductResponse;
import com.ecommerce.product_service.exception.ResourceNotFoundException;
import com.ecommerce.product_service.mapper.ProductMapper;
import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository repository;


    public ProductResponse create(ProductRequest productRequest) {

        log.info("Product creation request received: {}", productRequest);

        try {
            Product productEntity = ProductMapper.toProductEntity(productRequest);

            log.debug("Mapped request to entity: {}", productEntity);

            Product savedProduct = repository.save(productEntity);

            log.info("Product created successfully with id: {}", savedProduct.getId());

            return ProductMapper.toProductResponse(savedProduct);

        } catch (Exception ex) {

            log.error("Error while creating product: {}", productRequest, ex);

            throw ex;
        }
    }

    public List<ProductResponse> getAll() {

        log.info("Fetching all products request received");

        try {
            List<Product> productList = repository.findAll();

            log.info("Total products fetched from database: {}", productList.size());

            List<ProductResponse> responseList =
                    ProductMapper.toProductResponseList(productList);

            log.debug("Products mapped successfully to response DTO list");

            return responseList;

        } catch (Exception ex) {

            log.error("Error occurred while fetching all products", ex);

            throw ex;
        }
    }

    public ProductResponse getById(String id) {

        log.info("Product fetch initiated. productId={}", id);

        try {
            Product product = repository.findById(id)
                    .orElseThrow(() -> {
                        log.warn("Product not found in database. productId={}", id);
                        return new ResourceNotFoundException(
                                "Product not found with id: " + id
                        );
                    });

            log.debug("Product entity retrieved successfully. productId={}, categoryId={}, brand={}",
                    product.getId(),
                    product.getCategoryId(),
                    product.getBrand());

            ProductResponse response = ProductMapper.toProductResponse(product);

            log.info("Product fetch completed successfully. productId={}", id);

            return response;

        } catch (ResourceNotFoundException ex) {

            log.warn("Product fetch failed due to missing resource. productId={}", id);
            throw ex;

        } catch (Exception ex) {

            log.error("Unexpected error occurred while fetching product. productId={}", id, ex);
            throw new RuntimeException("Unable to fetch product at this time");
        }
    }

    public void delete(String id) {

        log.info("Product delete initiated. productId={}", id);

        try {
            boolean exists = repository.existsById(id);

            if (!exists) {
                log.warn("Product delete failed. Product not found. productId={}", id);
                throw new ResourceNotFoundException(
                        "Product not found with id: " + id
                );
            }

            log.debug("Product existence verified. Proceeding with delete. productId={}", id);

            repository.deleteById(id);

            log.info("Product deleted successfully. productId={}", id);

        } catch (ResourceNotFoundException ex) {

            log.warn("Product delete aborted due to missing resource. productId={}", id);
            throw ex;

        } catch (Exception ex) {

            log.error("Unexpected error occurred while deleting product. productId={}", id, ex);
            throw new RuntimeException("Unable to delete product at this time");
        }
    }
}
