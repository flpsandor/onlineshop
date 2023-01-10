package com.example.onlineshop.model.entity;


import com.example.onlineshop.model.enum_s.OrderStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Document
public class Order {
    @Id
    private UUID orderId;
    private LocalDateTime orderDateTime;
    private OrderStatus orderStatus;
    private Double orderPrice;

    private List<Product> orderProduct;
    private User orderUser;
}
