package com.example.onlineshop.collection.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "category")
public class Category {
    @Id
    private String categoryId;
    @Indexed(unique = true)
    private String categoryName;
}
