package com.example.onlineshop.service;

import com.example.onlineshop.entity.dto.UserCreationDto;
import com.example.onlineshop.entity.dto.UserDto;
import com.example.onlineshop.entity.dto.UserTypeCreationDto;
import com.example.onlineshop.entity.enum_s.UserType;
import com.example.onlineshop.exception.TokenNotValid;
import com.example.onlineshop.exception.UserNotExist;
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

    private Boolean tokenValidation(String token) throws UserNotExist, TokenNotValid {
        var Claims = jwtService.extractAllClaims(token.substring(7));
        var user = userRepository.findUserByUserEmail(Claims.getSubject()).orElseThrow(UserNotExist::new);
        if (jwtService.isTokenValid(token, user)) {
            throw new TokenNotValid();
        }
        var role = user.getUserType();
        return role.equals(UserType.ADMIN);
    }

    public UserDto setUserType(String token, String id, UserTypeCreationDto userTypeCreationDto) {
        return null;
    }

    public Void deleteUser(String token, String id) {
        return null;
    }

    public UserDto addUser(String token, UserCreationDto user) {
        return null;
    }
}
