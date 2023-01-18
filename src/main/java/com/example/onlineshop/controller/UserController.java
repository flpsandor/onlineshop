package com.example.onlineshop.controller;

import com.example.onlineshop.entity.dto.*;
import com.example.onlineshop.exception.TokenNotValid;
import com.example.onlineshop.exception.UserNotExist;
import com.example.onlineshop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/add-address")
    public ResponseEntity<UserWithAddressDto> addAddressForUser(@RequestHeader("Authentication") String token, @RequestParam ("id") String id,@Valid @RequestBody AddressCreationDto addressCreationDto) throws UserNotExist, TokenNotValid {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("user-address-add", "user-address-add");
        return new ResponseEntity<>(userService.addAddressForUser(token, id, addressCreationDto), responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/info")
    public ResponseEntity<UserWithAddressDto> allUserInfo(@RequestHeader("Authentication") String token, @RequestParam("id") String id) throws UserNotExist, TokenNotValid {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("user-info", "user-info");
        return new ResponseEntity<>(userService.allUserInfo(token, id), responseHeaders, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestHeader("Authentication") String token, @RequestParam ("id") String id) throws UserNotExist, TokenNotValid {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("delete-user", "delete-user");
        return new ResponseEntity<>(userService.deleteUser(token, id), responseHeaders, HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/update")
    public ResponseEntity<UserWithAddressDto> updateUser(@RequestHeader("Authentication") String token, @RequestParam ("id") String id, @Valid @RequestBody UserUpdateDto userUpdateDto) throws UserNotExist, TokenNotValid {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("update-user", "update-user");
        return new ResponseEntity<>(userService.updateUser(token, id, userUpdateDto), responseHeaders, HttpStatus.CREATED);
    }

    @PatchMapping("/passwd")
    public ResponseEntity<UserDto> changeUserPassword(@RequestHeader("Authentication") String token, @RequestParam ("id") String id, @Valid @RequestBody UserPasswordChangeDto userPasswordChangeDto) throws UserNotExist, TokenNotValid {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("password-change", "password-change");
        return new ResponseEntity<>(userService.changeUserPassword(token, id, userPasswordChangeDto), responseHeaders, HttpStatus.ACCEPTED);
    }
}
