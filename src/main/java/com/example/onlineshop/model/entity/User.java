package com.example.onlineshop.model.entity;

import com.example.onlineshop.model.enum_s.UserType;

import java.util.UUID;

public class User {
    private UUID userId;
    private String userEmail;
    private String userPassword;
    private UserType userType;
}
