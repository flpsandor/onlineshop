package com.example.onlineshop.service;

import com.example.onlineshop.entity.document.Order;
import com.example.onlineshop.entity.document.ShoppingCart;
import com.example.onlineshop.entity.document.User;
import com.example.onlineshop.entity.dto.OrderDto;
import com.example.onlineshop.entity.enum_s.OrderStatus;
import com.example.onlineshop.entity.pojo.CartProduct;
import com.example.onlineshop.exception.AddressInformationNotExist;
import com.example.onlineshop.exception.TokenNotValid;
import com.example.onlineshop.exception.UserNotExist;
import com.example.onlineshop.mapper.OrderMapper;
import com.example.onlineshop.repository.OrderRepository;
import com.example.onlineshop.repository.ShoppingCartRepository;
import com.example.onlineshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    OrderMapper orderMapper = OrderMapper.INSTANCE;
    private final ShoppingCartRepository shoppingCartRepository;

    public User extractEmail(String token) throws UserNotExist {
        var Claims = jwtService.extractAllClaims(token.substring(7));
        return userRepository.findUserByUserEmail(Claims.getSubject()).orElseThrow(UserNotExist::new);
    }

    public Double countPriceForOrder(List<CartProduct> products) {
        var price = 0.0;
        for (var product : products) {
            price += Math.pow(product.getProduct().getProductPrice(), product.getCount());
        }
        return price;
    }

    public OrderDto addOrder(String token, ShoppingCart cart) throws UserNotExist, TokenNotValid, AddressInformationNotExist {
        var user = extractEmail(token);
        if(!jwtService.isTokenValid(token.substring(7), user)){
            throw new TokenNotValid();
        }
        if(user.getUserAddress()==null){
            throw new AddressInformationNotExist();
        }
        var order = new Order();
        order.setOrderUser(user);
        order.setOrderStatus(OrderStatus.RECEIVED);
        order.setOrderProducts(cart.getShoppingCartProducts());
        order.setOrderDateTime(LocalDateTime.now());
        order.setOrderPrice(countPriceForOrder(cart.getShoppingCartProducts()));
        shoppingCartRepository.delete(cart);
        return orderMapper.orderToOrderDto(orderRepository.save(order));
    }
}
