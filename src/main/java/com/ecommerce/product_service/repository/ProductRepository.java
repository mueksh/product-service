package com.ecommerce.product_service.repository;

import com.ecommerce.product_service.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {

    //List<Product> findByCategoryId(String categoryId);

   // List<Product> findByBrand(String brand);

    //List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

   // Optional<Product> findByIdAndDeletedFalse(String id);

    //List<Product> findByCategoryIdAndDeletedFalse(String categoryId);

}
