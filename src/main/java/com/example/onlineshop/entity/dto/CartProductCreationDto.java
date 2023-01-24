package com.example.onlineshop.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProductCreationDto {
    @NotNull(message = "product is required")
    private String product;
    @NotNull(message = "count is required")
    private Integer count;
}
