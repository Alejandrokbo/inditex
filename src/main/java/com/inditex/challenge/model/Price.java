package com.inditex.challenge.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Data
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer priceId;

    private Date startDate;

    private Date endDate;

    private Integer priority;

    private Double price;

    private String currency;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product productId;

    public Product getProduct() {
        return productId;
    }

    public void setProduct(Product product) {
        this.productId = product;
    }
}



