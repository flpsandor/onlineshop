package com.example.onlineshop.service;

import com.example.onlineshop.collection.entity.User;
import com.example.onlineshop.repository.AddressRepository;
import com.example.onlineshop.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public UserService(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
