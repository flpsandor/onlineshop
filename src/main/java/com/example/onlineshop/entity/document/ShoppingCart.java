package com.example.onlineshop.entity.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCart {
    @Id
    private String shoppingCartId;
    private String shoppingCartUserInformation;
    private Map<Product, Integer> shoppingCartProducts = new HashMap<>();
    private LocalDateTime shoppingCartDate;
}
