package com.sean.productsapi.controller;

import com.sean.productsapi.jpa.model.Product;
import com.sean.productsapi.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam(value = "searchTerm", required = false) String searchTerm) {

        if (searchTerm == null) {
            return productService.getAllProducts();
        }

        return productService.getProductsBySearchTermIgnoreCase(searchTerm);
    }
}
