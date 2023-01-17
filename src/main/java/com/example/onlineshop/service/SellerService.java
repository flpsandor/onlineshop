package com.example.onlineshop.service;

import com.example.onlineshop.entity.dto.CategoryCreationDto;
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

@Service
@RequiredArgsConstructor
public class SellerService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final JwtService jwtService;

    ProductMapper productMapper = ProductMapper.INSTANCE;
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    private Boolean tokenValidation(String token) throws UserNotExist, TokenNotValid {
        var Claims = jwtService.extractAllClaims(token.substring(7));
        var user = userRepository.findUserByUserEmail(Claims.getSubject()).orElseThrow(UserNotExist::new);
        var role = user.getUserType();
        if (!jwtService.isTokenValid(token, user)) {
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

    public ProductDto updateProduct(String token, String id, ProductCreationDto product) throws ProductNotExist, UserNotAuthorized, TokenNotValid, UserNotExist {
        if (!tokenValidation(token)) {
            throw new UserNotAuthorized();
        }
        var productDb = productRepository.findById(id).orElseThrow(ProductNotExist::new);
        var productForSave = productMapper.productDtoToProduct(product);
        productForSave.setProductId(productDb.getProductId());
        productRepository.save(productDb);
        return productMapper.productToProductDto(productDb);
    }

    public Boolean deleteProduct(String token, String id) throws ProductNotExist, UserNotAuthorized, TokenNotValid, UserNotExist {
        if (!tokenValidation(token)) {
            throw new UserNotAuthorized();
        }
        var product = productRepository.findById(id).orElseThrow(ProductNotExist::new);
        productRepository.delete(product);
        return true;
    }

    public Boolean deleteCategory(String token, String id) throws CategoryNotExist, UserNotAuthorized, TokenNotValid, UserNotExist {
        if (!tokenValidation(token)) {
            throw new UserNotAuthorized();
        }
        categoryRepository.delete(categoryRepository.findById(id).orElseThrow(CategoryNotExist::new));
        return true;
    }

    public CategoryDto addCategory(String token, CategoryCreationDto category) throws UserNotAuthorized, TokenNotValid, UserNotExist {
        if (!tokenValidation(token)) {
            throw new UserNotAuthorized();
        }
        var categoryForSave = categoryRepository.save(categoryMapper.categoryCreationDtoToCategory(category));
        return categoryMapper.categoryToCategoryDto(categoryForSave);
    }

    public ProductDto addProductStock(String token, String id, Integer num) throws UserNotAuthorized, TokenNotValid, UserNotExist, ProductNotExist {
        if (!tokenValidation(token)) {
            throw new UserNotAuthorized();
        }
        var product = productRepository.findById(id).orElseThrow(ProductNotExist::new);
        product.setProductStock(product.getProductStock()+num);
        productRepository.save(product);
        return productMapper.productToProductDto(product);
    }
}
