package com.example.onlineshop.controller;

import com.example.onlineshop.entity.dto.*;
import com.example.onlineshop.exception.UserNotExist;
import com.example.onlineshop.exception.UserTypeNotValid;
import com.example.onlineshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("/{id}/delete")
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

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{id}/passwd")
    public UserDto changeUserPassword(@PathVariable ("id") String id, @Valid @RequestBody UserPasswordChangeDto userPasswordChangeDto) throws UserNotExist{
        return userService.changeUserPassword(id, userPasswordChangeDto);
    }
}
