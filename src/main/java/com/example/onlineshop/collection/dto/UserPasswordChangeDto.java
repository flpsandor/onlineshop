package com.example.onlineshop.collection.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordChangeDto {
    @Pattern(regexp = "^(?=.*?[A-Z])(?=(.*[a-z])+)(?=(.*\\d)+)(?=(.*\\W)+)(?!.*\\s).{8,}$",
            message = "password at least 8 characters and one letter and one number and one character")
    private String password;

    private String passwordCheck;
}
