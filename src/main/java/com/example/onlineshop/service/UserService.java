package com.example.onlineshop.service;

import com.example.onlineshop.entity.document.Address;
import com.example.onlineshop.entity.document.User;
import com.example.onlineshop.entity.dto.*;
import com.example.onlineshop.entity.enum_s.UserType;
import com.example.onlineshop.exception.*;
import com.example.onlineshop.mapper.UserMapper;
import com.example.onlineshop.repository.AddressRepository;
import com.example.onlineshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    UserMapper userMapper = UserMapper.INSTANCE;

    public UserService(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public UserDto addUser(UserCreationDto user) throws UserExist, PasswordNotMatch {
        var userForSave = userMapper.userCreationDtoToUser(user);
        if (userRepository.findUserByUserEmail(user.getEmail()).isPresent()) {
            throw new UserExist();
        }
        if (!user.getPassword().equals(user.getPasswordCheck())) {
            throw new PasswordNotMatch();
        }
        userForSave.setUserType(UserType.USER);
        return userMapper.userToUserDto(userForSave);
    }

    public UserDto findUserById(String id) throws UserNotExist {
        return userMapper.userToUserDto(userRepository.findById(id).orElseThrow(UserNotExist::new));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public UserWithAddressDto addAddressForUser(String id, AddressCreationDto addressCreationDto) throws UserNotExist {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotExist();
        }
        var address = addressRepository.save(userMapper.addressCreationWithDtoToAddress(addressCreationDto));
        user.get().setUserAddress(address);
        userRepository.save(user.get());
        return userMapper.userToUserWithAddressDto(user.get());
    }

    public UserWithAddressDto allUserInfo(String id) throws UserNotExist {
        return userMapper.userToUserWithAddressDto(userRepository.findById(id).orElseThrow(UserNotExist::new));
    }

    public UserWithAddressDto addUserWithAddress(UserCreationWithAddressDto user) throws UserExist, PasswordNotMatch {
        if (userRepository.findUserByUserEmail(user.getEmail()).isPresent()) {
            throw new UserExist();
        }
        if (!user.getPassword().equals(user.getPasswordCheck())) {
            throw new PasswordNotMatch();
        }
        var userForSave = userMapper.userCreationWithAddressDtoToUser(user);
        userForSave.setUserAddress(addressRepository.save(new Address(user.getStreet(), user.getCity(), user.getCityCode(), user.getState())));
        userForSave.setUserType(UserType.USER);
        return userMapper.userToUserWithAddressDto(userRepository.save(userForSave));
    }

    public void deleteUser(String id) throws UserNotExist {
        var user = userRepository.findById(id).orElseThrow(UserNotExist::new);
        addressRepository.delete(user.getUserAddress());
        userRepository.delete(user);
    }

    public UserDto setUserType(String id, UserTypeCreationDto userTypeCreationDto) throws UserNotExist, UserTypeNotValid {
        var userForUpdate = userRepository.findById(id).orElseThrow(UserNotExist::new);
        var userType = userTypeCreationDto.getType().toUpperCase();
        AtomicBoolean isValid = new AtomicBoolean(false);
        for (var type : UserType.values()) {
            if (type.toString().equals(userTypeCreationDto.getType())) {
                isValid.set(true);
            }
        }
        if(!isValid.get()){
            throw new UserTypeNotValid();
        }
        userForUpdate.setUserType(UserType.valueOf(userType));
        userRepository.save(userForUpdate);
        return userMapper.userToUserDto(userForUpdate);
    }

    public UserWithAddressDto updateUser(String id, UserUpdateDto userUpdateDto) throws UserNotExist {
        var userDb = userRepository.findById(id).orElseThrow(UserNotExist::new);
        var user = userMapper.userUpdateDtoToUser(userUpdateDto);
        user.setUserType(userDb.getUserType());
        user.setUserId(userDb.getUserId());
        return userMapper.userToUserWithAddressDto(user);
    }

    public UserDto changeUserPassword(String id, UserPasswordChangeDto userPasswordChangeDto) throws UserNotExist {
        var userDb = userRepository.findById(id).orElseThrow(UserNotExist::new);
        if (userPasswordChangeDto.getPassword().equals(userPasswordChangeDto.getPasswordCheck())) {
            userDb.setUserPassword(userPasswordChangeDto.getPassword());
        }
        return userMapper.userToUserDto(userRepository.save(userDb));
    }
}
