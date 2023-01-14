package com.example.onlineshop.collection.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationDto {
    @NotNull(message = "first name shouldnt be empty")
    private String firstName;
    @NotNull(message = "last name shouldnt be empty")
    private String lastName;
    @Email(message = "email is not valid")
    private String email;
    @Pattern(regexp = "^(?=.*?[A-Z])(?=(.*[a-z])+)(?=(.*\\d)+)(?=(.*\\W)+)(?!.*\\s).{8,}$",
            message = "password at least 8 characters and one letter and one number and one character")
    private String password;
    private String passwordCheck;
}
