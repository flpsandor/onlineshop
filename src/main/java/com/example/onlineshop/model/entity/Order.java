package com.example.onlineshop.model.entity;


import com.example.onlineshop.model.enum_s.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Order {
    private UUID orderId;
    private LocalDateTime orderDateTime;
    private OrderStatus orderStatus;
    private Double orderPrice;

    private List<Product> orderProduct;
    private User orderUser;
}
