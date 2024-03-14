package com.inditex.challenge.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private String productName;

    @ManyToOne
    @JoinColumn(name = "brandId")
    private Brand brand;

    @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL)
    private List<Price> prices;
}
