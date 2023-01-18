package com.example.onlineshop.service;

import com.example.onlineshop.entity.document.Order;
import com.example.onlineshop.entity.document.Product;
import com.example.onlineshop.entity.document.ShoppingCart;
import com.example.onlineshop.entity.document.User;
import com.example.onlineshop.entity.dto.OrderDto;
import com.example.onlineshop.entity.enum_s.OrderStatus;
import com.example.onlineshop.entity.enum_s.UserType;
import com.example.onlineshop.exception.OrderNotValid;
import com.example.onlineshop.exception.TokenNotValid;
import com.example.onlineshop.exception.UserNotAuthorized;
import com.example.onlineshop.exception.UserNotExist;
import com.example.onlineshop.mapper.OrderMapper;
import com.example.onlineshop.repository.OrderRepository;
import com.example.onlineshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    OrderMapper orderMapper = OrderMapper.INSTANCE;

    public User extractEmail(String token) throws UserNotExist {
        var Claims = jwtService.extractAllClaims(token.substring(7));
        return userRepository.findUserByUserEmail(Claims.getSubject()).orElseThrow(UserNotExist::new);
    }

    public boolean isTokenValid(String token, User user) throws TokenNotValid {
        if (!jwtService.isTokenValid(token, user)) {
            throw new TokenNotValid();
        }
        return true;
    }

    public Double countPriceForOrder(Map<Product, Integer> products) {
        var price = 0.0;
        for (var product : products.entrySet()) {
            price += product.getKey().getProductPrice() * product.getValue();
        }
        return price;
    }

    public OrderDto addOrder(String token, ShoppingCart cart) throws UserNotExist, TokenNotValid {
        var user = extractEmail(token);
        isTokenValid(token, user);
        //TODO check if address information exist
        var order = new Order();
        order.setOrderUser(user);
        order.setOrderStatus(OrderStatus.RECEIVED);
        order.setOrderProducts(cart.getShoppingCartProducts());
        order.setOrderDateTime(LocalDateTime.now());
        order.setOrderPrice(countPriceForOrder(cart.getShoppingCartProducts()));
        return orderMapper.orderToOrderDto(orderRepository.save(order));
    }

    public OrderDto getOrderInfo(String token, String id) throws UserNotExist, TokenNotValid, UserNotAuthorized, OrderNotValid {
        var user = extractEmail(token);
        var order = orderRepository.findById(id).orElseThrow(OrderNotValid::new);
        if((isTokenValid(token, user) && order.getOrderUser().equals(user))){
            throw new UserNotAuthorized();
        }
        return orderMapper.orderToOrderDto(order);
    }
}
