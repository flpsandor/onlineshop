package com.example.onlineshop.model.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Address{
    private String addressStreet;
    private String addressCity;
    private Integer addressCityCode;
    private String addressCityState;
}
