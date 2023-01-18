package com.example.onlineshop.service;

import com.example.onlineshop.entity.document.ShoppingCart;
import com.example.onlineshop.entity.dto.OrderDto;
import com.example.onlineshop.entity.dto.ShoppingCartCreationDto;
import com.example.onlineshop.entity.dto.ShoppingCartDto;
import com.example.onlineshop.exception.ShoppingCartNotExist;
import com.example.onlineshop.exception.TokenNotValid;
import com.example.onlineshop.exception.UserNotExist;
import com.example.onlineshop.mapper.ShoppingCartMapper;
import com.example.onlineshop.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderService orderService;
    ShoppingCartMapper shoppingCartMapper = ShoppingCartMapper.INSTANCE;

    public ShoppingCartDto addProductInCart(String id, ShoppingCartCreationDto shoppingCartCreationDto){
        var cart = shoppingCartRepository.findShoppingCartByShoppingCartUserInformation(id).orElse(new ShoppingCart());
        cart.setShoppingCartUserInformation(id);
        if (cart.getShoppingCartDate() != null) {
            cart.setShoppingCartDate(LocalDateTime.now());
        }
        var products = cart.getShoppingCartProducts();
        for (var product : cart.getShoppingCartProducts().entrySet()) {
            if (product.getKey().getProductId().equals(shoppingCartCreationDto.getProduct().getProductId())) {
                products.replace(product.getKey(), shoppingCartCreationDto.getCount());
            }
            products.put(shoppingCartCreationDto.getProduct(), shoppingCartCreationDto.getCount());
        }
        return shoppingCartMapper.shoppingCartToShoppingCartDto(cart);
    }

    public Boolean clearShoppingCart(String id) throws ShoppingCartNotExist {
        shoppingCartRepository.findShoppingCartByShoppingCartUserInformation(id).orElseThrow(ShoppingCartNotExist::new);
        return true;
    }

    public OrderDto addOrder(String token, String id) throws ShoppingCartNotExist, TokenNotValid, UserNotExist {
        var cart = shoppingCartRepository.findShoppingCartByShoppingCartUserInformation(id).orElseThrow(ShoppingCartNotExist::new);
        return orderService.addOrder(token, cart);
    }
}
