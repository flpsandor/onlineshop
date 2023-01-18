package com.example.onlineshop.service;

import com.example.onlineshop.entity.dto.*;
import com.example.onlineshop.entity.enum_s.OrderStatus;
import com.example.onlineshop.entity.enum_s.UserType;
import com.example.onlineshop.exception.*;
import com.example.onlineshop.mapper.CategoryMapper;
import com.example.onlineshop.mapper.OrderMapper;
import com.example.onlineshop.mapper.ProductMapper;
import com.example.onlineshop.repository.CategoryRepository;
import com.example.onlineshop.repository.OrderRepository;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final JwtService jwtService;

    private final OrderRepository orderRepository;

    OrderMapper orderMapper = OrderMapper.INSTANCE;
    ProductMapper productMapper = ProductMapper.INSTANCE;
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    private Boolean tokenValidation(String token) throws UserNotExist, TokenNotValid {
        token = token.substring(7);
        var Claims = jwtService.extractAllClaims(token);
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
        product.setProductStock(product.getProductStock() + num);
        productRepository.save(product);
        return productMapper.productToProductDto(product);
    }

    public List<OrderDto> getAllOrdersInfo(String token) throws UserNotExist, TokenNotValid, UserNotAuthorized {
        if (!tokenValidation(token)) {
            throw new UserNotAuthorized();
        }
        return orderRepository.findAll().stream().map(orderMapper::orderToOrderDto).toList();
    }

    public OrderDto getOrderInfo(String token, String id) throws TokenNotValid, UserNotExist, UserNotAuthorized, OrderNotValid {
        if (!tokenValidation(token)) {
            throw new UserNotAuthorized();
        }
        return orderMapper.orderToOrderDto(orderRepository.findById(id).orElseThrow(OrderNotValid::new));
    }

    public OrderDto changeStatus(String token, String id, String status) throws UserNotAuthorized, TokenNotValid, UserNotExist, OrderNotValid {
        if (!tokenValidation(token)) {
            throw new UserNotAuthorized();
        }
        var order = orderRepository.findById(id).orElseThrow(OrderNotValid::new);
        order.setOrderStatus(OrderStatus.valueOf(status.toUpperCase()));
        return orderMapper.orderToOrderDto(order);
    }

    public Boolean deleteOrder(String token, String id) throws OrderNotValid, UserNotAuthorized, TokenNotValid, UserNotExist {
        if (!tokenValidation(token)) {
            throw new UserNotAuthorized();
        }
        var order = orderRepository.findById(id).orElseThrow(OrderNotValid::new);
        orderRepository.delete(order);
        return true;
    }
}
