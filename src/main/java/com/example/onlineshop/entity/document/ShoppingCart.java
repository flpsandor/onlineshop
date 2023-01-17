package com.example.onlineshop.entity.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCart {
    private String shoppingCartEmail;
    private String shoppingCartUserToken;
    private Map<Product, Integer> shoppingCartProductList;
}
