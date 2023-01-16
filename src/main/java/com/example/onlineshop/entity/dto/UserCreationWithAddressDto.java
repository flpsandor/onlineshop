package com.example.onlineshop.entity.dto;

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
public class UserCreationWithAddressDto {
    @NotNull(message = "first name shouldnt be empty")
    private String firstName;
    @NotNull(message = "last name shouldnt be empty")
    private String lastName;
    @Email(message = "email is not in valid format")
    private String email;
    @Pattern(regexp = "^(?=.*?[A-Z])(?=(.*[a-z])+)(?=(.*\\d)+)(?=(.*\\W)+)(?!.*\\s).{8,}$",
            message = "password at least 8 characters and one letter and one number and one character")
    private String password;
    private String passwordCheck;
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
