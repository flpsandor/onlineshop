package com.example.onlineshop.entity.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection="address")
public class Address{
    @Id
    private String addressId;
    private String addressStreet;
    private String addressCity;
    private String addressCityCode;
    private String addressState;

    public Address(String street, String city, String cityCode, String state) {
        addressStreet=street;
        addressCity=city;
        addressCityCode=cityCode;
        addressStreet=state;
    }
}
