package com.example.onlineshop.mapper;
import com.example.onlineshop.entity.dto.ProductCreationDto;
import com.example.onlineshop.entity.dto.ProductDto;
import com.example.onlineshop.entity.document.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source="productName", target = "name")
    @Mapping(source="productPrice", target="price")
    @Mapping(source="productDescription", target="description")
    @Mapping(source="productCategory", target = "category")
    ProductDto productToProductDto(Product product);

    @Mapping(source="name", target = "productName")
    @Mapping(source="price", target="productPrice")
    @Mapping(source="description", target="productDescription")
    Product productDtoToProduct(ProductCreationDto productDto);
}
