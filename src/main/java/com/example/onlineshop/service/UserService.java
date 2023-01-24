package com.example.onlineshop.service;

import com.example.onlineshop.entity.document.User;
import com.example.onlineshop.entity.dto.*;
import com.example.onlineshop.exception.OrderNotValid;
import com.example.onlineshop.exception.TokenNotValid;
import com.example.onlineshop.exception.UserNotExist;
import com.example.onlineshop.mapper.OrderMapper;
import com.example.onlineshop.mapper.UserMapper;
import com.example.onlineshop.repository.AddressRepository;
import com.example.onlineshop.repository.OrderRepository;
import com.example.onlineshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final OrderRepository orderRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    UserMapper userMapper = UserMapper.INSTANCE;
    OrderMapper orderMapper = OrderMapper.INSTANCE;


    public UserWithAddressDto addAddressForUser(String token, String id, AddressCreationDto addressCreationDto) throws UserNotExist, TokenNotValid {
        User user = userRepository.findById(id).orElseThrow(UserNotExist::new);
        if (!jwtService.isTokenValid(token.substring(7), user)) {
            throw new TokenNotValid();
        }
        var address = addressRepository.save(userMapper.addressCreationWithDtoToAddress(addressCreationDto));
        user.setUserAddress(address);
        userRepository.save(user);
        return userMapper.userToUserWithAddressDto(user);
    }

    public UserWithAddressDto allUserInfo(String token, String id) throws UserNotExist, TokenNotValid {
        var user = userRepository.findById(id).orElseThrow(UserNotExist::new);
        if (!jwtService.isTokenValid(token.substring(7), user)) {
            throw new TokenNotValid();
        }
        return userMapper.userToUserWithAddressDto(user);
    }

    public Void deleteUser(String token, String id) throws UserNotExist, TokenNotValid {
        var user = userRepository.findById(id).orElseThrow(UserNotExist::new);
        if (!jwtService.isTokenValid(token.substring(7), user)) {
            throw new TokenNotValid();
        }
        addressRepository.delete(user.getUserAddress());
        userRepository.delete(user);
        return null;
    }

    public UserWithAddressDto updateUser(String token, String id, UserUpdateDto userUpdateDto) throws UserNotExist, TokenNotValid {
        var userDb = userRepository.findById(id).orElseThrow(UserNotExist::new);
        if(!jwtService.isTokenValid(token.substring(7), userDb)){
            throw new TokenNotValid();
        }
        var userForUpdate = userMapper.userUpdateDtoToUser(userUpdateDto);
        userForUpdate.setUserType(userDb.getUserType());
        userForUpdate.setUserId(userDb.getUserId());
        return userMapper.userToUserWithAddressDto(userForUpdate);
    }

    public UserDto changeUserPassword(String token, String id, UserPasswordChangeDto userPasswordChangeDto) throws UserNotExist, TokenNotValid {
        var userDb = userRepository.findById(id).orElseThrow(UserNotExist::new);
        if (!jwtService.isTokenValid(token.substring(7), userDb)) {
            throw new TokenNotValid();
        }
        if (userPasswordChangeDto.getPassword().equals(userPasswordChangeDto.getPasswordCheck())) {
            userDb.setUserPassword(passwordEncoder.encode(userPasswordChangeDto.getPassword()));
        }
        return userMapper.userToUserDto(userRepository.save(userDb));
    }

    public OrderDto getOrderInfo(String token, String id) throws UserNotExist, OrderNotValid {
        var user = userRepository.findUserByUserEmail(jwtService.extractAllClaims(token.substring(7)).getSubject()).orElseThrow(UserNotExist::new);
        var order = orderRepository.findById(id).orElseThrow(OrderNotValid::new);
        if(!order.getOrderUser().equals(user)){
            throw new OrderNotValid();
        }
        return orderMapper.orderToOrderDto(order);
    }

    public List<OrderDto> getOrdersInfo(String token) throws UserNotExist {
        var user = userRepository.findUserByUserEmail(jwtService.extractAllClaims(token.substring(7)).getSubject()).orElseThrow(UserNotExist::new);
        return orderRepository.findAllByOrderUser(user).stream().map(orderMapper::orderToOrderDto).collect(Collectors.toList());
    }
}
