package com.example.onlineshop.controller;

import com.example.onlineshop.entity.document.Product;
import com.example.onlineshop.entity.dto.CategoryDto;
import com.example.onlineshop.entity.dto.ProductCreationDto;
import com.example.onlineshop.entity.dto.ProductDto;
import com.example.onlineshop.exception.*;
import com.example.onlineshop.service.SellerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/seller/")
public class SellerController {
    private final SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity<ProductDto> addProduct(@RequestHeader("Authorization") String token, @Valid @RequestBody ProductCreationDto product) throws CategoryNotValid, UserNotAuthorized, UserNotExist, TokenNotValid {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("product-added", "product-added");
        return new ResponseEntity<>(sellerService.addProduct(token, product), responseHeaders, HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    public ResponseEntity<ProductDto> updateProduct(@RequestHeader("Authorization") String token, @RequestParam String id, @Valid @RequestBody Product product) throws ProductNotExist, UserNotAuthorized, TokenNotValid, UserNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("update-product", "update-product");
        return new ResponseEntity<>(sellerService.update(token, id, product), responseHeaders, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteProduct(@RequestHeader("Authorization") String token, @RequestParam("id") String id) throws ProductNotExist, UserNotAuthorized, TokenNotValid, UserNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("delete-product", "delete-product");
        return new ResponseEntity<>(sellerService.deleteProductById(token, id), responseHeaders, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/category/add")
    public ResponseEntity<CategoryDto> addCategory(@RequestHeader("Authorization") String token, @Valid @RequestBody CategoryDto category) throws UserNotAuthorized, TokenNotValid, UserNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("product-category-add", "product-category-add");
        return new ResponseEntity<>(sellerService.addCategory(token, category), responseHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/category/delete")
    public ResponseEntity<Void> deleteCategory(@RequestHeader("Authorization") String token, @RequestParam("id") String id) throws CategoryNotExist, UserNotAuthorized, TokenNotValid, UserNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("delete-product-category", "delete-product-category");
        return new ResponseEntity<>(sellerService.deleteCategory(token, id), responseHeaders, HttpStatus.NO_CONTENT);
    }
}
