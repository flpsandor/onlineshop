package com.example.onlineshop.repository;

import com.example.onlineshop.entity.document.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

}
