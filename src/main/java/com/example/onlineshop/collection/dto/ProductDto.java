package com.example.onlineshop.collection.dto;

import com.example.onlineshop.collection.document.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String name;
    private String description;
    private Double price;
    private Category category;
}
