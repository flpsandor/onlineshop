package com.example.onlineshop.controller;

import com.example.onlineshop.collection.document.User;
import com.example.onlineshop.collection.dto.*;
import com.example.onlineshop.exception.PasswordNotMatch;
import com.example.onlineshop.exception.UserExist;
import com.example.onlineshop.exception.UserNotExist;
import com.example.onlineshop.exception.UserTypeNotValid;
import com.example.onlineshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/find")
    public UserDto findUser(@PathVariable ("id") String id) throws UserNotExist {
        return userService.findUserById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public UserDto createUser(@Valid @RequestBody UserCreationDto user) throws UserExist, PasswordNotMatch {
        return userService.addUser(user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add-with-address")
    public UserWithAddressDto createUserWithAddress(@Valid @RequestBody UserCreationWithAddressDto user) throws UserExist, PasswordNotMatch {
        return userService.addUserWithAddress(user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}/add-address")
    public UserWithAddressDto addAddressForUser(@PathVariable ("id") String id,@Valid @RequestBody AddressCreationDto addressCreationDto) throws UserNotExist{
        return userService.addAddressForUser(id, addressCreationDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/info")
    public UserWithAddressDto allUserInfo(@PathVariable("id") String id) throws UserNotExist {
        return userService.allUserInfo(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/{id}/delete")
    public void deleteUser(@PathVariable ("id") String id) throws UserNotExist{
        userService.deleteUser(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("/{id}/update")
    public UserWithAddressDto updateUser(@PathVariable ("id") String id, @Valid @RequestBody UserUpdateDto userUpdateDto) throws UserNotExist {
        return userService.updateUser(id, userUpdateDto);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/{id}/usertype")
    public UserDto setUserType(@PathVariable ("id") String id, @Valid @RequestBody UserTypeCreationDto userTypeCreationDto) throws UserTypeNotValid, UserNotExist {
        return userService.setUserType(id, userTypeCreationDto);
    }

    //FOR TESTING
    //DELETE AFTER
    @GetMapping("/all")
    public List<User> listAllUser(){
        return userService.findAll();
    }
}
