package com.example.onlineshop.mapper;

import com.example.onlineshop.entity.dto.CategoryCreationDto;
import com.example.onlineshop.entity.dto.CategoryDto;
import com.example.onlineshop.entity.document.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "categoryId", target = "id")
    @Mapping(source="categoryName", target = "name")
    CategoryDto categoryToCategoryDto(Category category);

    @Mapping(source="name", target ="categoryName")
    Category categoryCreationDtoToCategory(CategoryCreationDto category);
}
