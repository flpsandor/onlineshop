package com.example.onlineshop.collection.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWithAddressDto {
    private String firstName;
    private String lastName;
    private String email;

    private String type;
    private String street;
    private String city;
    private String cityCode;
    private String state;
}