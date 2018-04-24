package com.sean.productsapi.service;

import com.sean.productsapi.jpa.model.Product;
import com.sean.productsapi.jpa.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Service
@Validated
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductsBySearchTermIgnoreCase(@NotBlank String searchTerm) {
        return productRepository.findBySearchTermIgnoreCase(searchTerm.toLowerCase());
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
