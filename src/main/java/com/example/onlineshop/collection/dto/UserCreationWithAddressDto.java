package com.example.onlineshop.collection.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationWithAddressDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String passwordCheck;
    private String street;
    private String city;
    private String cityCode;
    private String state;
}
