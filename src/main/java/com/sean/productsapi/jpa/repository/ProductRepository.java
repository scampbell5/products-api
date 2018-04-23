package com.sean.productsapi.jpa.repository;

import com.sean.productsapi.jpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(" SELECT product FROM Product product " +
           " WHERE product.description = :description ")
    Optional<Product> findByDescription(@Param("description") String description);

}