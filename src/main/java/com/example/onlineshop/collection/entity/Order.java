package com.example.onlineshop.collection.entity;


import com.example.onlineshop.collection.enum_s.OrderStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "order")
public class Order {
    @Id
    private String orderId;
    private LocalDateTime orderDateTime;
    private OrderStatus orderStatus;
    private Double orderPrice;

    @DBRef
    private List<Product> orderProduct;
    @DBRef
    private User orderUser;
}
