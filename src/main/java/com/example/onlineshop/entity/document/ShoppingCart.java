package com.example.onlineshop.entity.document;

import com.example.onlineshop.entity.pojo.CartProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "shoppingcart")
public class ShoppingCart {
    @Id
    private String shoppingCartId;
    private String shoppingCartUserInformation;
    private List<CartProduct> shoppingCartProducts = new ArrayList<>();
    private LocalDateTime shoppingCartDate;
}
