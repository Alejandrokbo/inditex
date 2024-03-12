package com.inditex.challenge.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int brandId;

    private String brandName;

    @OneToMany(mappedBy = "brandId", cascade = CascadeType.ALL)
    private List<Product> products;

}
