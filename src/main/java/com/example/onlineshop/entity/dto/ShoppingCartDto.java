package com.example.onlineshop.entity.dto;

import com.example.onlineshop.entity.pojo.CartProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDto {

    private String id;
    private String user;
    private List<CartProduct> products;
    private String date;
}
