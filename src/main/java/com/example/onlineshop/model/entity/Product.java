package com.example.onlineshop.model.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Product {
    private UUID productId;
    private String productName;
    private String productDescription;
    private Double productPrice;
}
