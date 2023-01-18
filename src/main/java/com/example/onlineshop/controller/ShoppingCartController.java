package com.example.onlineshop.controller;

import com.example.onlineshop.entity.dto.OrderDto;
import com.example.onlineshop.entity.dto.ShoppingCartCreationDto;
import com.example.onlineshop.entity.dto.ShoppingCartDto;
import com.example.onlineshop.exception.ShoppingCartNotExist;
import com.example.onlineshop.exception.TokenNotValid;
import com.example.onlineshop.exception.UserNotExist;
import com.example.onlineshop.service.ShoppingCartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @PostMapping("/cart/add")
    private ResponseEntity<ShoppingCartDto> addProductInCart(@RequestParam ("id") String id,  @Valid @RequestBody ShoppingCartCreationDto shoppingCartCreationDto){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("item-in-cart", "item-in-cart");
        return new ResponseEntity<>(shoppingCartService.addProductInCart(id, shoppingCartCreationDto), responseHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/cart/clear")
    private ResponseEntity<Boolean> clearCart(@RequestParam("id") String id) throws ShoppingCartNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(shoppingCartService.clearShoppingCart(id), responseHeaders, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/cart/add-order")
    private ResponseEntity<OrderDto> addOrder(@RequestHeader("Authorization") String token, @RequestParam("id") String id) throws ShoppingCartNotExist, TokenNotValid, UserNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("order add", "order add");
        return new ResponseEntity<>(shoppingCartService.addOrder(token, id), responseHeaders, HttpStatus.CREATED);
    }
}
