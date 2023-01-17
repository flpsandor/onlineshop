package com.example.onlineshop.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreationDto {
    @NotNull(message = "category shouldnt be empty")
    private String name;
}
