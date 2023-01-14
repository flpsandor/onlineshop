package com.example.onlineshop.collection.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreationDto {
    @NotNull(message = "product name shouldnt be empty")
    @Size(min=3, max=30, message = "min character for name is 3, max is 30")
    private String name;
    @NotNull(message = "description name shouldnt be empty")
    @Size(min=10, max=1000, message = "min character for name is 10, max is 1000")
    private String description;
    @NumberFormat
    private Double price;
    @NotNull(message = "category is required")
    private String category;
}

