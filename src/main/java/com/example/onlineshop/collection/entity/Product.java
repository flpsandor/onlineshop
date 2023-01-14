package com.example.onlineshop.collection.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "product")
public class Product {
    @Id
    private String productId;
    private String productName;
    private String productDescription;
    private Double productPrice;

    @DBRef
    private Category productCategory;
}
