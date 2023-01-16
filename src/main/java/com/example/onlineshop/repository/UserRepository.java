package com.example.onlineshop.repository;


import com.example.onlineshop.entity.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findUserByUserEmail(String userEmail);
}
