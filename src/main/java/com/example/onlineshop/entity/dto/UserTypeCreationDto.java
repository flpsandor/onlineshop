package com.example.onlineshop.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTypeCreationDto {
    @NotNull(message = "type shouldnt be empty")
    public String type;
}
