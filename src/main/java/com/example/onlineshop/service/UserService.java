package com.example.onlineshop.service;

import com.example.onlineshop.collection.document.Address;
import com.example.onlineshop.collection.document.User;
import com.example.onlineshop.collection.dto.*;
import com.example.onlineshop.collection.enum_s.UserType;
import com.example.onlineshop.exception.*;
import com.example.onlineshop.mapper.UserMapper;
import com.example.onlineshop.repository.AddressRepository;
import com.example.onlineshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public UserWithAddressDto addAddressForUser(String id,AddressCreationDto addressCreationDto) throws UserNotExist {
        var user = userRepository.findById(id);
        if(user.isEmpty()){
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
        var address = new Address();
        address.setAddressStreet(user.getStreet());
        address.setAddressCity(user.getCity());
        address.setAddressCityCode(user.getCityCode());
        address.setAddressState(user.getState());
        addressRepository.save(address);
        userForSave.setUserAddress(address);
        userForSave.setUserType(UserType.USER);
        userRepository.save(userForSave);
        return userMapper.userToUserWithAddressDto(userForSave);
    }

    public void deleteUser(String id) throws UserNotExist {
        var user = userRepository.findById(id).orElseThrow(UserNotExist::new);
        addressRepository.delete(user.getUserAddress());
        userRepository.delete(user);
    }

    public UserDto setUserType(String id, UserTypeCreationDto userTypeCreationDto) throws UserNotExist, UserTypeNotValid {
        var userForUpdate = userRepository.findById(id).orElseThrow(UserNotExist::new);
        var userType =  userTypeCreationDto.getType().toUpperCase();
        // check if value of type is in enums
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
}
