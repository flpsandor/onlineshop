package com.example.onlineshop.service;

import com.example.onlineshop.entity.pojo.CartProduct;
import com.example.onlineshop.entity.document.ShoppingCart;
import com.example.onlineshop.entity.dto.OrderDto;
import com.example.onlineshop.entity.dto.CartProductCreationDto;
import com.example.onlineshop.entity.dto.ShoppingCartDto;
import com.example.onlineshop.exception.*;
import com.example.onlineshop.mapper.ShoppingCartMapper;
import com.example.onlineshop.repository.ProductRepository;
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
    private final ProductRepository productRepository;

    private boolean isOnStock(Integer stock, Integer count) throws NotEnoughtStock {
        if (stock < count) {
            throw new NotEnoughtStock();
        }
        return true;
    }

    public ShoppingCartDto addProductInCart(String id, CartProductCreationDto cartProductCreationDto) throws NotEnoughtStock, ProductNotExist {
        ShoppingCart cart = shoppingCartRepository.findShoppingCartByShoppingCartUserInformation(id).orElse(new ShoppingCart());
        var product = productRepository.findById(cartProductCreationDto.getProduct()).orElseThrow(ProductNotExist::new);
        var count = cartProductCreationDto.getCount();
        cart.setShoppingCartUserInformation(id);
        cart.setShoppingCartDate(LocalDateTime.now());
        var products = cart.getShoppingCartProducts();
        if(isOnStock(product.getProductStock(), count)){
            products.add(new CartProduct(product, count));
        }
        shoppingCartRepository.save(cart);
        return shoppingCartMapper.shoppingCartToShoppingCartDto(cart);
    }

    public Boolean clearShoppingCart(String id) throws ShoppingCartNotExist {
        var cart = shoppingCartRepository.findShoppingCartByShoppingCartUserInformation(id).orElseThrow(ShoppingCartNotExist::new);
        shoppingCartRepository.delete(cart);
        return true;
    }

    public OrderDto addOrder(String token, String id) throws ShoppingCartNotExist, TokenNotValid, UserNotExist, AddressInformationNotExist {
        var cart = shoppingCartRepository.findShoppingCartByShoppingCartUserInformation(id).orElseThrow(ShoppingCartNotExist::new);
        return orderService.addOrder(token, cart);
    }
}
