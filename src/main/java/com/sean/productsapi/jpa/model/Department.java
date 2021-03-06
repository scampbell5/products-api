package com.sean.productsapi.jpa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "department")
public class Department {

    public Department(String name) {
        setName(name);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "department_name", unique = true, nullable = false)
    private String name;

    public void setName(String name) {
        this.name = name.trim();
    }
}