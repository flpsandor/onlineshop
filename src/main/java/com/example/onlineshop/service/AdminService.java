package com.example.onlineshop.service;

import com.example.onlineshop.entity.dto.UserDto;
import com.example.onlineshop.entity.dto.UserTypeCreationDto;
import com.example.onlineshop.entity.enum_s.UserType;
import com.example.onlineshop.exception.TokenNotValid;
import com.example.onlineshop.exception.UserNotAuthorized;
import com.example.onlineshop.exception.UserNotExist;
import com.example.onlineshop.mapper.UserMapper;
import com.example.onlineshop.repository.AddressRepository;
import com.example.onlineshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final JwtService jwtService;
    UserMapper userMapper = UserMapper.INSTANCE;


    private Boolean tokenValidation(String token) throws UserNotExist, TokenNotValid {
        token = token.substring(7);
        var Claims = jwtService.extractAllClaims(token);
        var user = userRepository.findUserByUserEmail(Claims.getSubject()).orElseThrow(UserNotExist::new);
        var role = user.getUserType();
        if (!jwtService.isTokenValid(token, user)) {
            throw new TokenNotValid();
        }
        return role.equals(UserType.ADMIN);
    }

    public UserDto setUserType(String token, String id, UserTypeCreationDto userTypeCreationDto) throws UserNotAuthorized, TokenNotValid, UserNotExist {
        if (!tokenValidation(token)) {
            throw new UserNotAuthorized();
        }
        var user = userRepository.findById(id).orElseThrow(UserNotExist::new);
        var userType = userMapper.userTypeDtoToUser(userTypeCreationDto);
        user.setUserType(userType.getUserType());
        return userMapper.userToUserDto(user);
    }

    public Boolean deleteUser(String token, String id) throws TokenNotValid, UserNotExist, UserNotAuthorized {
        if (!tokenValidation(token)) {
            throw new UserNotAuthorized();
        }
        var user = userRepository.findById(id).orElseThrow(UserNotExist::new);
        if(user.getUserAddress()!=null){
            addressRepository.delete(user.getUserAddress());
        }
        userRepository.delete(user);
        return true;
    }
}
