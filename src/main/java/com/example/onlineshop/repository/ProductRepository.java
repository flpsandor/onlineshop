package com.example.onlineshop.repository;

import com.example.onlineshop.entity.document.Category;
import com.example.onlineshop.entity.document.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findProductByProductName(String name);
    List<Product> findProductByProductCategory(Category category);


}
