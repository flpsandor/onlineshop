package com.example.onlineshop.mapper;

import com.example.onlineshop.entity.document.Order;
import com.example.onlineshop.entity.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source="orderId", target = "id")
    @Mapping(source="orderUser", target = "user")
    @Mapping(source="orderUser.userAddress", target = "address")
    @Mapping(source="orderProducts", target = "products")
    @Mapping(source="orderPrice", target = "price")
    @Mapping(source = "orderStatus", target = "status")
    OrderDto orderToOrderDto(Order order);
}
