package com.example.onlineshop.entity.dto;

import com.example.onlineshop.entity.document.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String id;
    private String name;
    private String description;
    private Double price;
    private Category category;

    private Integer stock;
}
