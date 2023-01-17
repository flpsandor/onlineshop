package com.example.onlineshop.service;

import com.example.onlineshop.entity.dto.ProductDto;
import com.example.onlineshop.exception.CategoryNotExist;
import com.example.onlineshop.exception.NoProducts;
import com.example.onlineshop.exception.ProductNotExist;
import com.example.onlineshop.mapper.CategoryMapper;
import com.example.onlineshop.mapper.ProductMapper;
import com.example.onlineshop.repository.CategoryRepository;
import com.example.onlineshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    ProductMapper productMapper = ProductMapper.INSTANCE;
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    public List<ProductDto> productList() throws NoProducts {
        var products = productRepository.findAll().stream().map(productMapper::productToProductDto).toList();
        if (products.isEmpty()) {
            throw new NoProducts();
        }
        return products;
    }

    public ProductDto findProductById(String id) throws ProductNotExist {
        return productMapper.productToProductDto(productRepository.findById(id).orElseThrow(ProductNotExist::new));
    }


    public ProductDto findProductByName(String name) throws ProductNotExist {
        return productMapper.productToProductDto(productRepository.findProductByProductName(name).orElseThrow(ProductNotExist::new));
    }

    public List<ProductDto> getAllProductInCategory(String id) throws CategoryNotExist {
        var products = productRepository.findProductByProductCategory(categoryRepository.findById(id).orElseThrow(CategoryNotExist::new));
        return products.stream().map(productMapper::productToProductDto).toList();
    }
}
