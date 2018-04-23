package com.sean.productsapi.jpa.repository;

import com.sean.productsapi.ProductsApiApplicationTests;
import com.sean.productsapi.jpa.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class DepartmentRepositoryTests extends ProductsApiApplicationTests {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void saveShouldSaveExpected() {
        String name = "department name";
        Department expected = new Department(name);

        departmentRepository.save(expected);

        // No exceptions should be thrown
    }

    @Test
    public void findByNameShouldReturnExpected() {
        String name = "new department name";
        Department expected = departmentRepository.saveAndFlush(new Department(name));

        Optional<Department> optionalActual = departmentRepository.findByName(name);

        Assert.assertTrue(optionalActual.isPresent());
        Assert.assertEquals(expected.getId(), optionalActual.get().getId());
        Assert.assertEquals(expected.getName(), optionalActual.get().getName());
    }
}