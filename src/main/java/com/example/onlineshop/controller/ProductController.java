package com.example.onlineshop.controller;

import com.example.onlineshop.entity.dto.CategoryDto;
import com.example.onlineshop.entity.dto.ProductDto;
import com.example.onlineshop.exception.CategoryNotExist;
import com.example.onlineshop.exception.NoCategories;
import com.example.onlineshop.exception.NoProducts;
import com.example.onlineshop.exception.ProductNotExist;
import com.example.onlineshop.service.ProductService;
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

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> productList() throws NoProducts {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("products-information", "products-information");
        return new ResponseEntity<>(productService.productList(), responseHeaders, HttpStatus.OK);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<ProductDto> findProductById(@RequestParam("id") String id) throws ProductNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("product-information", "product-information");
        return new ResponseEntity<>(productService.findProductById(id), responseHeaders, HttpStatus.OK);
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<ProductDto> findProductByName(@RequestParam("name") String name) throws ProductNotExist {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("product-information", "product-information");
        return new ResponseEntity<>(productService.findProductByName(name), responseHeaders, HttpStatus.OK);
    }

    @GetMapping("/all/category")
    public ResponseEntity<List<ProductDto>> getAllProductInCategory(@RequestParam("id") String id) throws CategoryNotExist, NoProducts {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("products-information-from-category", "products-information-from-category");
        return new ResponseEntity<>(productService.getAllProductInCategory(id), responseHeaders, HttpStatus.OK);
    }

    @GetMapping("/category/all")
    public ResponseEntity<List<CategoryDto>> getAllCategories() throws NoCategories {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("categories", "categories");
        return new ResponseEntity<>(productService.getAllCategory(), responseHeaders, HttpStatus.OK);
    }
}
