package com.example.onlineshop.controller;

import com.example.onlineshop.entity.document.Product;
import com.example.onlineshop.entity.dto.CategoryDto;
import com.example.onlineshop.entity.dto.ProductCreationDto;
import com.example.onlineshop.entity.dto.ProductDto;
import com.example.onlineshop.exception.*;
import com.example.onlineshop.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> productList() throws NoProducts {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("products-information", "products-information");
        return new ResponseEntity<>(productService.productList(), responseHeaders, HttpStatus.OK);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/find-by-id")
    public ResponseEntity<ProductDto> findProductById(@RequestParam("id") String id) throws ProductNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("product-information", "product-information");
        return new ResponseEntity<>(productService.findProductById(id), responseHeaders, HttpStatus.OK);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/find-by-name")
    public ResponseEntity<ProductDto> findProductByName(@RequestParam("name") String name) throws ProductNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("product-information", "product-information");
        return new ResponseEntity<>(productService.findProductByName(name), responseHeaders, HttpStatus.OK);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/add")
    public ResponseEntity<ProductDto> addProduct(@RequestHeader("Authorization") String token, @Valid @RequestBody ProductCreationDto product) throws CategoryNotValid, UserNotAuthorized, UserNotExist, TokenNotValid {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("product-added", "product-added");
        return new ResponseEntity<>(productService.addProduct(token, product), responseHeaders, HttpStatus.CREATED);
    }

    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @PatchMapping("/update")
    public ResponseEntity<ProductDto> updateProduct(@RequestHeader("Authorization") String token, @RequestParam String id, @Valid @RequestBody Product product) throws ProductNotExist, UserNotAuthorized, TokenNotValid, UserNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("update-product", "update-product");
        return new ResponseEntity<>(productService.update(token, id, product), responseHeaders, HttpStatus.ACCEPTED);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteProduct(@RequestHeader("Authorization") String token, @RequestParam("id") String id) throws ProductNotExist, UserNotAuthorized, TokenNotValid, UserNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("delete-product", "delete-product");
        return new ResponseEntity<>(productService.deleteProductById(token, id), responseHeaders, HttpStatus.NO_CONTENT);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/all/category")
    public ResponseEntity<List<ProductDto>> getAllProductInCategory(@RequestParam("id") String id) throws CategoryNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("products-information-from-category", "products-information-from-category");
        return new ResponseEntity<>(productService.getAllProductInCategory(id), responseHeaders, HttpStatus.OK);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/category/add")
    public ResponseEntity<CategoryDto> addCategory(@RequestHeader("Authorization") String token, @Valid @RequestBody CategoryDto category) throws UserNotAuthorized, TokenNotValid, UserNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("product-category-add", "product-category-add");
        return new ResponseEntity<>(productService.addCategory(token, category), responseHeaders, HttpStatus.CREATED);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/category/delete")
    public ResponseEntity<Void> deleteCategory(@RequestHeader("Authorization") String token, @RequestParam("id") String id) throws CategoryNotExist, UserNotAuthorized, TokenNotValid, UserNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("delete-product-category", "delete-product-category");
        return new ResponseEntity<>(productService.deleteCategory(token, id), responseHeaders, HttpStatus.NO_CONTENT);
    }
}
