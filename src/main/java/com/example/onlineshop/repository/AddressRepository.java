package com.example.onlineshop.repository;

import com.example.onlineshop.model.entity.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends MongoRepository<Address, UUID> {
}
