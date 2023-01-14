package com.example.onlineshop.repository;

import com.example.onlineshop.collection.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface OrderRepository extends MongoRepository<Order, String> {
}
