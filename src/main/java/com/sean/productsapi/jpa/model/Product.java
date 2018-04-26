package com.sean.productsapi.jpa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_external_id", nullable = false, unique = true)
    private String externalId;

    @Column(name = "product_description", nullable = false)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "product_last_sold_date")
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

    @Column(name = "product_cost", nullable = false)
    private String cost;

    public void setExternalId(String externalId) {
        this.externalId = externalId.trim();
    }

    public void setDescription(String description) {
        this.description = description.trim();
    }

    public void setLastSoldDate(Date lastSoldDate) {
        this.lastSoldDate = lastSoldDate;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife.trim();
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setPrice(String price) {
        this.price = price.trim();
    }

    public void setUnit(String unit) {
        this.unit = unit.trim();
    }

    public void setXFor(String xFor) {
        this.xFor = xFor.trim();
    }

    public void setCost(String cost) {
        this.cost = cost.trim();
    }
}