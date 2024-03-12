package com.inditex.challenge.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product {
    @Id
    private Long productId;

    private String productName;

    @ManyToOne
    @JoinColumn(name = "brandId")
    private Brand brandId;

    @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL)
    private List<Price> prices;
}
