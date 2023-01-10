package com.example.onlineshop.model.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document
public class Product {
    @Id
    private UUID productId;
    private String productName;
    private String productDescription;
    private Double productPrice;
}
