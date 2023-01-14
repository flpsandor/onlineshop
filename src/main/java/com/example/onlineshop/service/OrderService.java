package com.example.onlineshop.service;

import com.example.onlineshop.repository.OrderRepository;
import com.example.onlineshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }
}
