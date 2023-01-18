package com.example.onlineshop.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ExceptionDto {
    public String title;
    public String details;
    public Integer status;
    public String errorType;
}
