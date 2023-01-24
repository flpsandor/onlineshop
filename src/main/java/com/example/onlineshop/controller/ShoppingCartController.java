package com.example.onlineshop.controller;

import com.example.onlineshop.entity.dto.OrderDto;
import com.example.onlineshop.entity.dto.CartProductCreationDto;
import com.example.onlineshop.entity.dto.ShoppingCartDto;
import com.example.onlineshop.exception.*;
import com.example.onlineshop.service.ShoppingCartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    private ResponseEntity<ShoppingCartDto> addProductInCart(@RequestParam ("id") String id,  @Valid @RequestBody CartProductCreationDto productInCartCreationDto) throws NotEnoughtStock, ProductNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("item-in-cart", "item-in-cart");
        return new ResponseEntity<>(shoppingCartService.addProductInCart(id, productInCartCreationDto), responseHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/clear")
    private ResponseEntity<Boolean> clearCart(@RequestParam("id") String id) throws ShoppingCartNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(shoppingCartService.clearShoppingCart(id), responseHeaders, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/add-order")
    private ResponseEntity<OrderDto> addOrder(@RequestHeader("Authorization") String token, @RequestParam("id") String id) throws ShoppingCartNotExist, TokenNotValid, UserNotExist, AddressInformationNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("order add", "order add");
        return new ResponseEntity<>(shoppingCartService.addOrder(token, id), responseHeaders, HttpStatus.CREATED);
    }
}
