package com.inditex.challenge.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer brandId;

    private String brandName;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<Product> products;

}
