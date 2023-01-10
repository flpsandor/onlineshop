package com.example.onlineshop.model.entity;

import com.example.onlineshop.model.enum_s.UserType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Document
public class User {
    @Id
    private UUID userId;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPassword;
    private UserType userType;

    private Address userAddress;
    private List<Order> userOrders;
}
