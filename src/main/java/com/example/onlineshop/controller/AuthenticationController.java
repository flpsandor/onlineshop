package com.example.onlineshop.controller;

import com.example.onlineshop.entity.dto.AuthenticationRequest;
import com.example.onlineshop.entity.dto.AuthenticationResponse;
import com.example.onlineshop.entity.dto.UserCreationDto;
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
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserCreationDto userCreationDto){
        return ResponseEntity.ok(authenticationService.reqister(userCreationDto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest authenticationRequest) throws UserNotExist {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

}
