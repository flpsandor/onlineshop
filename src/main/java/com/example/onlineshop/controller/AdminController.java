package com.example.onlineshop.controller;

import com.example.onlineshop.entity.dto.UserCreationDto;
import com.example.onlineshop.entity.dto.UserDto;
import com.example.onlineshop.entity.dto.UserTypeCreationDto;
import com.example.onlineshop.exception.TokenNotValid;
import com.example.onlineshop.exception.UserNotExist;
import com.example.onlineshop.exception.UserTypeNotValid;
import com.example.onlineshop.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/usertype")
    public ResponseEntity<UserDto> setUserType(@RequestHeader("Authentication") String token, @RequestParam ("id") String id, @Valid @RequestBody UserTypeCreationDto userTypeCreationDto) throws UserNotExist, TokenNotValid {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("user-type", "user-type");
        return new ResponseEntity<>(adminService.setUserType(token, id, userTypeCreationDto), responseHeaders, HttpStatus.CREATED);
    }

    @PostMapping("/add-seller-user")
    public ResponseEntity<UserDto> addSeller(@RequestHeader("Authentication") String token, @Valid @RequestBody UserCreationDto user){
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("add-seller","add-seller");
        return new ResponseEntity<>(adminService.addUser(token, user), responseHeader, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<Void> deleteUser(@RequestHeader("Authentication") String token, @RequestParam ("id") String id){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("delete-user", "delete-user");
        return new ResponseEntity<>(adminService.deleteUser(token, id), responseHeaders, HttpStatus.NO_CONTENT);
    }


}
