package com.sean.productsapi.jpa.repository;

import com.sean.productsapi.ProductsApiApplicationTests;
import com.sean.productsapi.jpa.model.Department;
import com.sean.productsapi.jpa.model.Product;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ProductRepositoryTests extends ProductsApiApplicationTests {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void saveShouldSaveExpected() {
        Department department = new Department("test department");
        department = departmentRepository.save(department);

        Product product = new Product();

        product.setDescription("Random description");
        product.setCost("$1");
        product.setLastSoldDate(new Date());
        product.setShelfLife("30d");
        product.setXFor("3");
        product.setUnit("1lb");
        product.setDepartment(department);
        product.setPrice("$2");

        product = productRepository.save(product);

        // No exceptions should be thrown
    }
}