package com.example.onlineshop.entity.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="address")
public class Address{
    @Id
    private String addressId;
    private String addressStreet;
    private String addressCity;
    private String addressCityCode;
    private String addressState;
}
