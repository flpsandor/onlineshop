package com.example.onlineshop.entity.dto;

import com.example.onlineshop.entity.document.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartCreationDto {
    @NotNull(message = "user identification is required")
    private String user;
    @NotNull(message = "product identification is required")
    private Product product;
    @Min(value=1)
    @NotNull(message = "count is required")
    private Integer count;
}
