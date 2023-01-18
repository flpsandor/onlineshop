package com.example.onlineshop.controller;

import com.example.onlineshop.entity.dto.LoginDto;
import com.example.onlineshop.entity.dto.TokenDto;
import com.example.onlineshop.entity.dto.UserCreationDto;
import com.example.onlineshop.exception.PasswordNotMatch;
import com.example.onlineshop.exception.UserExist;
import com.example.onlineshop.exception.UserNotExist;
import com.example.onlineshop.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<TokenDto> register(@RequestBody UserCreationDto user) throws UserExist, PasswordNotMatch {
        return ResponseEntity.ok(authenticationService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto login) throws UserNotExist, PasswordNotMatch {
        return ResponseEntity.ok(authenticationService.login(login));
    }

}
