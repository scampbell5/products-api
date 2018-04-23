package com.sean.productsapi.jpa.repository;

import com.sean.productsapi.jpa.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(" SELECT department from Department department " +
           " WHERE department.name = :name ")
    Optional<Department> findByName(@Param("name") String name);
}