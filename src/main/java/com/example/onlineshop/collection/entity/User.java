package com.example.onlineshop.collection.entity;

import com.example.onlineshop.collection.enum_s.UserType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection="user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    private String userId;
    private String userFirstName;
    private String userLastName;
    @Indexed(unique = true)
    private String userEmail;
    private String userPassword;
    private UserType userType;
    @DBRef
    private Address userAddress;
    @DBRef
    private List<Order> userOrders;
}
