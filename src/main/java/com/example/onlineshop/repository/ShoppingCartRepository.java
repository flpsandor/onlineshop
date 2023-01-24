package com.example.onlineshop.repository;

import com.example.onlineshop.entity.document.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {
    Optional<ShoppingCart> findShoppingCartByShoppingCartUserInformation(String id);

}
