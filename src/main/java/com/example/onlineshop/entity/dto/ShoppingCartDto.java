package com.example.onlineshop.entity.dto;

import com.example.onlineshop.entity.document.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDto {
    private String id;
    private String user;
    private Map<Product, Integer> products;
    private String date;
}
