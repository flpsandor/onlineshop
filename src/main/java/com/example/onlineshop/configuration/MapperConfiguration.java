package com.example.onlineshop.configuration;

import com.example.onlineshop.mapper.CategoryMapper;
import com.example.onlineshop.mapper.OrderMapper;
import com.example.onlineshop.mapper.ProductMapper;
import com.example.onlineshop.mapper.UserMapper;
import org.springframework.context.annotation.Bean;

public class MapperConfiguration {
    @Bean
    ProductMapper productMapper() {
        return ProductMapper.INSTANCE;
    }

    @Bean
    CategoryMapper categoryMapper() {
        return CategoryMapper.INSTANCE;
    }

    @Bean
    UserMapper userMapper() {
        return UserMapper.INSTANCE;
    }

    @Bean
    OrderMapper orderMapper() {
        return OrderMapper.INSTANCE;
    }
}
