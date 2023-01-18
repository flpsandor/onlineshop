package com.example.onlineshop.entity.dto;

import com.example.onlineshop.entity.document.Address;
import com.example.onlineshop.entity.document.Product;
import com.example.onlineshop.entity.document.User;
import com.example.onlineshop.entity.enum_s.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String id;
    private User user;
    private Address address;
    private OrderStatus status;
    private Double price;
    private LocalDateTime dateTime;
    private Map<Product, Integer> products;

}
