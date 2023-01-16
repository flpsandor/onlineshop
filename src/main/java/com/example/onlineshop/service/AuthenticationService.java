package com.example.onlineshop.service;

import com.example.onlineshop.entity.document.User;
import com.example.onlineshop.entity.dto.AuthenticationRequest;
import com.example.onlineshop.entity.dto.AuthenticationResponse;
import com.example.onlineshop.entity.dto.UserCreationDto;
import com.example.onlineshop.entity.enum_s.UserType;
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

    public AuthenticationResponse reqister(UserCreationDto userCreationDto) {
        var user = userMapper.userCreationDtoToUser(userCreationDto);
        user  = User.builder()
                .userFirstName(user.getUserFirstName())
                .userLastName(user.getUserLastName())
                .userEmail(user.getUserEmail())
                .userPassword(passwordEncoder.encode(user.getUserPassword()))
                .userType(UserType.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    //login
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws UserNotExist {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        var user = userRepository.findUserByUserEmail(authenticationRequest.getEmail()).orElseThrow(UserNotExist::new);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
