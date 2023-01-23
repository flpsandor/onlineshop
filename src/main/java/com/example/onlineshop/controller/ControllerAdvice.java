package com.example.onlineshop.controller;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.onlineshop.entity.dto.ExceptionDto;
import com.example.onlineshop.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.io.DecodingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;
import java.util.*;

@RestControllerAdvice
public class ControllerAdvice {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> handleValidationArgument(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(ExceptionDto.builder().title("Field input errors").details(errorMap.toString()).status(HttpStatus.BAD_REQUEST.value()).errorType(MethodArgumentNotValidException.class.getSimpleName()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserTypeNotValid.class)
    public ResponseEntity<ExceptionDto> handleUserTypeNotValidException(UserTypeNotValid exception) {
        return new ResponseEntity<>(com.example.onlineshop.entity.dto.ExceptionDto.builder().title("User type not valid").details(exception.getMessage()).status(HttpStatus.UNAUTHORIZED.value()).errorType(UserTypeNotValid.class.getSimpleName()).build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CategoryNotExist.class)
    public ResponseEntity<ExceptionDto> handleCategoryNotExistException(CategoryNotExist exception) {
        return new ResponseEntity<>(com.example.onlineshop.entity.dto.ExceptionDto.builder().title("Category not exist").details(exception.getMessage()).status(HttpStatus.NOT_FOUND.value()).errorType(CategoryNotExist.class.getSimpleName()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotValid.class)
    public ResponseEntity<ExceptionDto> handleCategoryNotValidException(CategoryNotValid exception) {
        return new ResponseEntity<>(com.example.onlineshop.entity.dto.ExceptionDto.builder().title("Category not valid").details(exception.getMessage()).status(HttpStatus.NOT_ACCEPTABLE.value()).errorType(CategoryNotValid.class.getSimpleName()).build(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ProductNotExist.class)
    public ResponseEntity<ExceptionDto> handleProductNotExistException(ProductNotExist exception) {
        return new ResponseEntity<>(com.example.onlineshop.entity.dto.ExceptionDto.builder().title("Product not exist").details(exception.getMessage()).status(HttpStatus.NOT_FOUND.value()).errorType(ProductNotExist.class.getSimpleName()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserExist.class)
    public ResponseEntity<ExceptionDto> handleUserExistException(UserExist exception) {
        return new ResponseEntity<>(com.example.onlineshop.entity.dto.ExceptionDto.builder().title("User already exist").details(exception.getMessage()).status(HttpStatus.CONFLICT.value()).errorType(UserExist.class.getSimpleName()).build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotExist.class)
    public ResponseEntity<ExceptionDto> handleUserNotExistException(UserNotExist exception) {
        return new ResponseEntity<>(com.example.onlineshop.entity.dto.ExceptionDto.builder().title("User not exist").details(exception.getMessage()).status(HttpStatus.NOT_FOUND.value()).errorType(UserNotExist.class.getSimpleName()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionDto> handleNullPointerException(NullPointerException exception) {
        return new ResponseEntity<>(com.example.onlineshop.entity.dto.ExceptionDto.builder().title("Null pointer exception").details(exception.getMessage()).status(HttpStatus.INTERNAL_SERVER_ERROR.value()).errorType(NullPointerException.class.getSimpleName()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionDto> handleAuthentionException(AuthenticationException exception) {
        return new ResponseEntity<>(com.example.onlineshop.entity.dto.ExceptionDto.builder().title("Authentication exception").details(exception.getMessage()).status(HttpStatus.FORBIDDEN.value()).errorType(AuthenticationException.class.getSimpleName()).build(), HttpStatus.FORBIDDEN);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(NoProducts.class)
    public String handleNoProductsException(NoProducts exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(NoCategories.class)
    public ResponseEntity<ExceptionDto> handleNoCategoriesException(NoCategories exception) {
        return new ResponseEntity<>(com.example.onlineshop.entity.dto.ExceptionDto.builder().title("Category dont have any item").details(exception.getMessage()).status(HttpStatus.NO_CONTENT.value()).errorType(NoCategories.class.getSimpleName()).build(), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(UserNotAuthorized.class)
    public ResponseEntity<ExceptionDto> handleUserNotAuthorized(UserNotAuthorized exception) {
        return new ResponseEntity<>(com.example.onlineshop.entity.dto.ExceptionDto.builder().title("User not authorized").details(exception.getMessage()).status(HttpStatus.UNAUTHORIZED.value()).errorType(UserNotAuthorized.class.getSimpleName()).build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TokenNotValid.class)
    public ResponseEntity<ExceptionDto> handleTokenNotValid(TokenNotValid exception) {
        return new ResponseEntity<>(com.example.onlineshop.entity.dto.ExceptionDto.builder().title("Token not valid").details(exception.getMessage()).status(HttpStatus.UNAUTHORIZED.value()).errorType(TokenNotValid.class.getSimpleName()).build(), HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ExceptionDto> handleSignatureException(SignatureException exception) {
        return new ResponseEntity<>(com.example.onlineshop.entity.dto.ExceptionDto.builder().title("Signature exception").details(exception.getMessage()).status(HttpStatus.FORBIDDEN.value()).errorType(SignatureException.class.getSimpleName()).build(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ExceptionDto> handleExpiredJwtException(ExpiredJwtException exception) {
        return new ResponseEntity<>(com.example.onlineshop.entity.dto.ExceptionDto.builder().title("Expired jwt exception").details(exception.getMessage()).status(HttpStatus.FORBIDDEN.value()).errorType(ExpiredJwtException.class.getSimpleName()).build(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ShoppingCartNotExist.class)
    public ResponseEntity<ExceptionDto> handleShoppingCartNotExistException(ShoppingCartNotExist exception) {
        return new ResponseEntity<>(com.example.onlineshop.entity.dto.ExceptionDto.builder().title("Shopping cart not exist").details(exception.getMessage()).status(HttpStatus.NOT_FOUND.value()).errorType(ShoppingCartNotExist.class.getSimpleName()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ExceptionDto> handleTokenExpiredException(TokenExpiredException exception) {
        return new ResponseEntity<>(com.example.onlineshop.entity.dto.ExceptionDto.builder().title("Token expired").details(exception.getMessage()).status(HttpStatus.UNAUTHORIZED.value()).errorType(TokenExpiredException.class.getSimpleName()).build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DecodingException.class)
    public ResponseEntity<ExceptionDto> handleDecodingException(DecodingException exception) {
        return new ResponseEntity<>(com.example.onlineshop.entity.dto.ExceptionDto.builder().title("Decoding exception").details(exception.getMessage()).status(HttpStatus.UNAUTHORIZED.value()).errorType(DecodingException.class.getSimpleName()).build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(PasswordNotMatch.class)
    public ResponseEntity<ExceptionDto> handlePasswordNotMatchException(PasswordNotMatch exception) {
        return new ResponseEntity<>(com.example.onlineshop.entity.dto.ExceptionDto.builder().title("password problem").details(exception.getMessage()).status(HttpStatus.CONFLICT.value()).errorType(PasswordNotMatch.class.getSimpleName()).build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotEnoughtStock.class)
    public ResponseEntity<ExceptionDto> handleNotEnoguhtStock(NotEnoughtStock exception) {
        return new ResponseEntity<>(ExceptionDto.builder().title("Not eneought product on stock").details(exception.getMessage()).status(HttpStatus.NOT_ACCEPTABLE.value()).errorType(NotEnoughtStock.class.getSimpleName()).build(), HttpStatus.NOT_ACCEPTABLE);
    }
}
