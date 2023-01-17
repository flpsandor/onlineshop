package com.example.onlineshop.service;

import com.example.onlineshop.entity.document.User;
import com.example.onlineshop.entity.dto.LoginDto;
import com.example.onlineshop.entity.dto.TokenDto;
import com.example.onlineshop.entity.dto.UserCreationDto;
import com.example.onlineshop.entity.enum_s.UserType;
import com.example.onlineshop.exception.UserExist;
import com.example.onlineshop.exception.UserNotExist;
import com.example.onlineshop.mapper.UserMapper;
import com.example.onlineshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    UserMapper userMapper = UserMapper.INSTANCE;

    public TokenDto reqister(UserCreationDto user) throws UserExist {
        var userForSave = userMapper.userCreationDtoToUser(user);
        if (userRepository.findUserByUserEmail(userForSave.getUserEmail()).isPresent()) {
            throw new UserExist();
        }
        userForSave  = User.builder()
                .userFirstName(userForSave.getUserFirstName())
                .userLastName(userForSave.getUserLastName())
                .userEmail(userForSave.getUserEmail())
                .userPassword(passwordEncoder.encode(userForSave.getUserPassword()))
                .userType(UserType.USER)
                .build();
        userRepository.save(userForSave);
        var jwtToken = jwtService.generateToken(userForSave);
        return TokenDto.builder().token(jwtToken).build();
    }

    //login
    public TokenDto login(LoginDto login) throws UserNotExist {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword()
                )
        );
        var user = userRepository.findUserByUserEmail(login.getEmail()).orElseThrow(UserNotExist::new);
        var jwtToken = jwtService.generateToken(user);
        return TokenDto.builder().token(jwtToken).build();
    }
}
