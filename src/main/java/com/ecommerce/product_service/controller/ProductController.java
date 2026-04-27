package com.ecommerce.product_service.controller;

import com.ecommerce.product_service.dao.ProductRequest;
import com.ecommerce.product_service.dao.ProductResponse;
import com.ecommerce.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> create(
            @RequestBody ProductRequest productRequest) {

        log.info("Product create API request received.");

        try {
            ProductResponse productResponse =
                    productService.create(productRequest);

            log.info("Product created successfully via API. productId={}",
                    productResponse.getId());

            return ResponseEntity.ok(productResponse);

        } catch (Exception ex) {

            log.error("Error occurred while processing product create API request.", ex);
            throw ex;
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {

        log.info("Product list API request received.");

        try {
            List<ProductResponse> responseList =
                    productService.getAll();

            log.info("Product list fetched successfully via API. totalRecords={}",
                    responseList.size());

            return ResponseEntity.ok(responseList);

        } catch (Exception ex) {

            log.error("Error occurred while processing product list API request.", ex);
            throw ex;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(
            @PathVariable String id) {

        log.info("Product fetch API request received. productId={}", id);

        try {
            ProductResponse response =
                    productService.getById(id);

            log.info("Product fetched successfully via API. productId={}", id);

            return ResponseEntity.ok(response);

        } catch (Exception ex) {

            log.error("Error occurred while processing product fetch API request. productId={}", id, ex);
            throw ex;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable String id) {

        log.info("Product delete API request received. productId={}", id);

        try {
            productService.delete(id);

            log.info("Product deleted successfully via API. productId={}", id);

            return ResponseEntity.noContent().build();

        } catch (Exception ex) {

            log.error("Error occurred while processing product delete API request. productId={}", id, ex);
            throw ex;
        }
    }
}
