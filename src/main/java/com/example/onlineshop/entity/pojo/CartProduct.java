package com.example.onlineshop.entity.pojo;

import com.example.onlineshop.entity.document.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProduct {
    private Product product;
    private Integer count;
}
