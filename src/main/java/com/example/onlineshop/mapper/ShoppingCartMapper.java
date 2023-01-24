package com.example.onlineshop.mapper;

import com.example.onlineshop.entity.document.ShoppingCart;
import com.example.onlineshop.entity.dto.ShoppingCartDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShoppingCartMapper {
    ShoppingCartMapper INSTANCE = Mappers.getMapper(ShoppingCartMapper.class);

    @Mapping(source="shoppingCartId", target = "id")
    @Mapping(source="shoppingCartUserInformation", target = "user")
    @Mapping(target="products", source = "shoppingCartProducts")
    @Mapping(target = "date", source = "shoppingCartDate")
    ShoppingCartDto shoppingCartToShoppingCartDto(ShoppingCart shoppingCart);
}
