package com.example.onlineshop.mapper;

import com.example.onlineshop.collection.dto.CategoryDto;
import com.example.onlineshop.collection.document.Category;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source="categoryName", target = "name")
    CategoryDto categoryToCategoryDto(Category category);

    @InheritInverseConfiguration
    Category categoryDtoToCategory(CategoryDto categoryDto);
}
