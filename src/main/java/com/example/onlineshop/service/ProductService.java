package com.example.onlineshop.service;

import com.example.onlineshop.entity.document.Product;
import com.example.onlineshop.entity.dto.CategoryDto;
import com.example.onlineshop.entity.dto.ProductCreationDto;
import com.example.onlineshop.entity.dto.ProductDto;
import com.example.onlineshop.entity.enum_s.UserType;
import com.example.onlineshop.exception.*;
import com.example.onlineshop.mapper.CategoryMapper;
import com.example.onlineshop.mapper.ProductMapper;
import com.example.onlineshop.repository.CategoryRepository;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final JwtService jwtService;
    ProductMapper productMapper = ProductMapper.INSTANCE;
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;
    private final UserRepository userRepository;

    private Boolean tokenValidation(String token) throws UserNotExist, TokenNotValid {
        token = token.substring(7);
        var Claims = jwtService.extractAllClaims(token);
        var user = userRepository.findUserByUserEmail(Claims.getSubject()).orElseThrow(UserNotExist::new);
        var role = user.getUserType();
        if (jwtService.isTokenValid(token, user)) {
            throw new TokenNotValid();
        }
        return role.equals(UserType.SELLER);
    }

    public ProductDto addProduct(String token, ProductCreationDto product) throws CategoryNotValid, UserNotAuthorized, UserNotExist, TokenNotValid {
        if (!tokenValidation(token)) {
            throw new UserNotAuthorized();
        }
        var productForSave = productMapper.productDtoToProduct(product);
        productForSave.setProductCategory(categoryRepository.findById(product.getCategory()).orElseThrow(CategoryNotValid::new));
        productRepository.save(productForSave);
        return productMapper.productToProductDto(productForSave);
    }

    public List<ProductDto> productList() throws NoProducts {
        var products = productRepository.findAll().stream().map(productMapper::productToProductDto).toList();
        if (products.isEmpty()) {
            throw new NoProducts();
        }
        return products;
    }

    public ProductDto update(String token, String id, Product product) throws ProductNotExist, UserNotAuthorized, TokenNotValid, UserNotExist {
        if (!tokenValidation(token)) {
            throw new UserNotAuthorized();
        }
        var productDb = productRepository.findById(id).orElseThrow(ProductNotExist::new);
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

    public Void deleteProductById(String token, String id) throws ProductNotExist, UserNotAuthorized, TokenNotValid, UserNotExist {
        if (!tokenValidation(token)) {
            throw new UserNotAuthorized();
        }
        productRepository.findById(id).orElseThrow(ProductNotExist::new);
        return productRepository.deleteByProductId(id);
    }

    public ProductDto findProductByName(String name) throws ProductNotExist {
        return productMapper.productToProductDto(productRepository.findProductByProductName(name).orElseThrow(ProductNotExist::new));
    }

    public List<ProductDto> getAllProductInCategory(String id) throws CategoryNotExist {
        var products = productRepository.findProductByProductCategory(categoryRepository.findById(id).orElseThrow(CategoryNotExist::new));
        return products.stream().map(productMapper::productToProductDto).toList();
    }

    public CategoryDto addCategory(String token, CategoryDto category) throws UserNotAuthorized, TokenNotValid, UserNotExist {
        if (!tokenValidation(token)) {
            throw new UserNotAuthorized();
        }
        return categoryMapper.categoryToCategoryDto(categoryRepository.save(categoryMapper.categoryDtoToCategory(category)));
    }

    public Void deleteCategory(String token, String id) throws CategoryNotExist, UserNotAuthorized, TokenNotValid, UserNotExist {
        if (!tokenValidation(token)) {
            throw new UserNotAuthorized();
        }
        categoryRepository.findById(id).orElseThrow(CategoryNotExist::new);
        return categoryRepository.deleteByCategoryId(id);
    }
}
