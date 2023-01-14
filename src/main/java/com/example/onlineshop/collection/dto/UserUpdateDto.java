package com.example.onlineshop.collection.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {
    @NotNull(message = "first name shouldnt be empty")
    private String firstName;
    @NotNull(message = "last name shouldnt be empty")
    private String lastName;
    @Email(message = "email is not in valid format")
    private String email;
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
