package com.example.onlineshop.controller;

import com.example.onlineshop.entity.dto.*;
import com.example.onlineshop.exception.*;
import com.example.onlineshop.service.SellerService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/seller/")
public class SellerController {
    private final SellerService sellerService;

    @PostMapping("/product/add")
    public ResponseEntity<ProductDto> addProduct(@RequestHeader("Authorization") String token, @Valid @RequestBody ProductCreationDto product) throws CategoryNotValid, UserNotAuthorized, UserNotExist, TokenNotValid {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("product-added", "product-added");
        return new ResponseEntity<>(sellerService.addProduct(token, product), responseHeaders, HttpStatus.CREATED);
    }

    @PatchMapping("/product/update")
    public ResponseEntity<ProductDto> updateProduct(@RequestHeader("Authorization") String token, @RequestParam String id, @Valid @RequestBody ProductCreationDto product) throws ProductNotExist, UserNotAuthorized, TokenNotValid, UserNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("update-product", "update-product");
        return new ResponseEntity<>(sellerService.updateProduct(token, id, product), responseHeaders, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/product/delete")
    public ResponseEntity<Boolean> deleteProduct(@RequestHeader("Authorization") String token, @RequestParam("id") String id) throws ProductNotExist, UserNotAuthorized, TokenNotValid, UserNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("delete-product", "delete-product");
        return new ResponseEntity<>(sellerService.deleteProduct(token, id), responseHeaders, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/product/stock")
    public ResponseEntity<ProductDto> addProductStock(@RequestHeader("Authorization") String token, @RequestParam ("id") String id, @NonNull @RequestParam("num") Integer num) throws UserNotAuthorized, TokenNotValid, UserNotExist, ProductNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("add-product-stock","add-product-stock");
        return new ResponseEntity<>(sellerService.addProductStock(token, id, num), responseHeaders,HttpStatus.CREATED);
    }

    @PostMapping("/category/add")
    public ResponseEntity<CategoryDto> addCategory(@RequestHeader("Authorization") String token, @Valid @RequestBody CategoryCreationDto category) throws UserNotAuthorized, TokenNotValid, UserNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("product-category-add", "product-category-add");
        return new ResponseEntity<>(sellerService.addCategory(token, category), responseHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/category/delete")
    public ResponseEntity<Boolean> deleteCategory(@RequestHeader("Authorization") String token, @RequestParam("id") String id) throws CategoryNotExist, UserNotAuthorized, TokenNotValid, UserNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("delete-product-category", "delete-product-category");
        return new ResponseEntity<>(sellerService.deleteCategory(token, id), responseHeaders, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/order/status")
    public ResponseEntity<OrderDto> changeStatus(@RequestHeader("Authorization") String token, @RequestParam ("id") String id, @RequestParam("status") String status) throws UserNotAuthorized, OrderNotValid, TokenNotValid, UserNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("change-status", "change-status");
        return new ResponseEntity<>(sellerService.changeStatus(token, id, status), responseHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/order/delete")
    public ResponseEntity<Boolean> deleteOrder(@RequestHeader("Authorization") String token, @RequestParam("id") String id) throws UserNotAuthorized, OrderNotValid, TokenNotValid, UserNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("delete-order", "delete-order");
        return new ResponseEntity<>(sellerService.deleteOrder(token, id), responseHeaders, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/order/all")
    public ResponseEntity<List<OrderDto>> getAllOrdersInfo(@RequestHeader("Authorization") String token) throws TokenNotValid, UserNotExist, UserNotAuthorized {
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("orders-info", "orders-info");
        return new ResponseEntity<>(sellerService.getAllOrdersInfo(token), responseHeader, HttpStatus.OK);
    }

    @GetMapping("/order/info")
    public ResponseEntity<OrderDto> getOrderInfo(@RequestHeader("Authorization") String token, @NonNull @RequestParam String id) throws UserNotAuthorized, OrderNotValid, TokenNotValid, UserNotExist {
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("order-info", "order-info");
        return new ResponseEntity<>(sellerService.getOrderInfo(token, id), responseHeader, HttpStatus.OK);
    }
}
