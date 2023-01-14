package com.example.onlineshop.collection.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreationDto {
    private String name;
    private String description;
    private Double price;
    private String category;
}

