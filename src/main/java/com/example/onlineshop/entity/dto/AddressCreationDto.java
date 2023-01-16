package com.example.onlineshop.entity.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressCreationDto {
    @NotNull(message = "street name shouldnt be empty")
    private String street;
    @NotNull(message = "city shouldnt be empty")
    private String city;
    @NumberFormat()
    @Size(min = 5, max = 5, message = "citycode is not in valid format")
    private String cityCode;
    @NotNull(message = "state shouldnt be empty")
    private String state;

}
