package com.example.onlineshop.entity.dto;

import com.example.onlineshop.entity.document.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartCreationDto {
    private String user;
    private Product product;
    private Integer count;
}
