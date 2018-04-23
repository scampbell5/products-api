package com.sean.productsapi.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_description", nullable = false)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "product_last_sold_date", nullable = false)
    private Date lastSoldDate;

    @Column(name = "product_shelf_life", nullable = false)
    private String shelfLife;

    @ManyToOne
    @JoinColumn(name = "product_department_id", referencedColumnName = "department_id", nullable = false)
    private Department department;

    @Column(name = "product_price", nullable = false)
    private String price;

    @Column(name = "product_unit", nullable = false)
    private String unit;

    @Column(name = "product_x_for", nullable = false)
    private String xFor;

    @Column(name = "product_cost")
    private String cost;
}