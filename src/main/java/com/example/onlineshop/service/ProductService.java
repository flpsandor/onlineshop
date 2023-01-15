package com.example.onlineshop.service;

import com.example.onlineshop.collection.dto.CategoryDto;
import com.example.onlineshop.collection.dto.ProductCreationDto;
import com.example.onlineshop.collection.dto.ProductDto;
import com.example.onlineshop.collection.document.Product;
import com.example.onlineshop.exception.CategoryNotExist;
import com.example.onlineshop.exception.CategoryNotValid;
import com.example.onlineshop.exception.ProductNotExist;
import com.example.onlineshop.mapper.CategoryMapper;
import com.example.onlineshop.mapper.ProductMapper;
import com.example.onlineshop.repository.CategoryRepository;
import com.example.onlineshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    ProductMapper productMapper = ProductMapper.INSTANCE;
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductDto addProduct(ProductCreationDto product) throws CategoryNotValid {
        var productForSave = productMapper.productDtoToProduct(product);
        productForSave.setProductCategory(categoryRepository.findById(product.getCategory()).orElseThrow(CategoryNotValid::new));
        productRepository.save(productForSave);
        return productMapper.productToProductDto(productForSave);
    }

    public List<ProductDto> productList() {
        return productRepository.findAll().stream().map(productMapper::productToProductDto).toList();
    }

    public ProductDto update(String productId, Product product) throws ProductNotExist {
        var productDb = productRepository.findById(productId).orElseThrow(ProductNotExist::new);
        productDb.setProductName(product.getProductName());
        productDb.setProductDescription(product.getProductDescription());
        productDb.setProductPrice(product.getProductPrice());
        productDb.setProductCategory(product.getProductCategory());
        productRepository.save(productDb);
        return productMapper.productToProductDto(productDb);
    }

    public ProductDto findProductById(String id) throws ProductNotExist {
        return productMapper.productToProductDto(productRepository.findById(id).orElseThrow(ProductNotExist::new));
    }

    public void deleteProductById(String id) throws ProductNotExist {
        productRepository.delete(productRepository.findById(id).orElseThrow(ProductNotExist::new));
    }

    public ProductDto findProductByName(String name) throws ProductNotExist {
        return productMapper.productToProductDto(productRepository.findProductByProductName(name).orElseThrow(ProductNotExist::new));
    }

    public List<ProductDto> getAllProductInCategory(String id) throws CategoryNotExist {
        var products = productRepository.findProductByProductCategory(categoryRepository.findById(id).orElseThrow(CategoryNotExist::new));
        return products.stream().map(productMapper::productToProductDto).toList();
    }

    public CategoryDto addCategory(CategoryDto category) {
        return categoryMapper.categoryToCategoryDto(categoryRepository.save(categoryMapper.categoryDtoToCategory(category)));
    }

    public void deleteCategory(String id) throws CategoryNotExist {
        categoryRepository.delete(categoryRepository.findById(id).orElseThrow(CategoryNotExist::new));
    }
}
