package com.example.onlineshop.repository;

import com.example.onlineshop.entity.document.Order;
import com.example.onlineshop.entity.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findAllByOrderUser(User user);
}
