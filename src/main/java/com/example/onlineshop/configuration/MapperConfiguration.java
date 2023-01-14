package com.example.onlineshop.configuration;

import com.example.onlineshop.mapper.CategoryMapper;
import com.example.onlineshop.mapper.ProductMapper;
import org.springframework.context.annotation.Bean;

public class MapperConfiguration {
    @Bean
    ProductMapper productMapper(){
        return ProductMapper.INSTANCE;
    }

    @Bean
    CategoryMapper categoryMapper(){
        return CategoryMapper.INSTANCE;
    }
}
