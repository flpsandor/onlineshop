package com.example.onlineshop.controller;

import com.example.onlineshop.entity.dto.OrderDto;
import com.example.onlineshop.exception.OrderNotValid;
import com.example.onlineshop.exception.TokenNotValid;
import com.example.onlineshop.exception.UserNotAuthorized;
import com.example.onlineshop.exception.UserNotExist;
import com.example.onlineshop.service.OrderService;
import com.example.onlineshop.service.SellerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
}
