package com.example.onlineshop.collection.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "address")
public class Address{
    @Id
    private String addressId;
    private String addressStreet;
    private String addressCity;
    private Integer addressCityCode;
    private String addressCityState;
}
