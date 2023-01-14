package com.example.onlineshop.controller;

import com.example.onlineshop.collection.dto.CategoryDto;
import com.example.onlineshop.collection.dto.ProductCreationDto;
import com.example.onlineshop.collection.dto.ProductDto;
import com.example.onlineshop.collection.document.Product;
import com.example.onlineshop.exception.CategoryNotExist;
import com.example.onlineshop.exception.CategoryNotValid;
import com.example.onlineshop.exception.ProductNotExist;
import com.example.onlineshop.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/all")
    public List<ProductDto> productList(){
        return productService.productList();
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/{id}/findById")
    public ProductDto findProductById(@PathVariable ("id") String id) throws ProductNotExist {
        return productService.findProductById(id);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("{name}/findByName")
    public ProductDto findProductByName(@PathVariable("name") String name) throws ProductNotExist {
        return productService.findProductByName(name);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/add")
    public ProductDto addProduct(@RequestBody ProductCreationDto product) throws CategoryNotValid {
        return productService.addProduct(product);
    }

    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @PatchMapping("/{id}/update")
    public Product updateProduct(@PathVariable String id, @RequestBody Product product) throws ProductNotExist {
        return productService.update(id,product);
    }

    @ResponseStatus(code= HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}/delete")
    public void deleteProduct(@PathVariable ("id") String id) throws ProductNotExist {
        productService.deleteProductById(id);
    }

    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @GetMapping("/all/{id}/category")
    public List<ProductDto> getAllProductInCategory(@PathVariable ("id") String id) throws CategoryNotExist {
        return productService.getAllProductInCategory(id);
    }

    @PostMapping("/category/add")
    public CategoryDto addCategory(@RequestBody CategoryDto category){
        return productService.addCategory(category);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/category/{id}/delete")
    public void deleteCategory(@PathVariable ("id") String id) throws CategoryNotExist {
        productService.deleteCategory(id);
    }
}
