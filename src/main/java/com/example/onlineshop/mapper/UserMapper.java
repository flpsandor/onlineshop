package com.example.onlineshop.mapper;

import com.example.onlineshop.entity.document.Address;
import com.example.onlineshop.entity.document.User;
import com.example.onlineshop.entity.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source="userId", target = "id")
    @Mapping(source = "userFirstName", target = "firstName")
    @Mapping(source = "userLastName", target = "lastName")
    @Mapping(source = "userEmail", target = "email")
    @Mapping(target = "type", source = "userType")
    UserDto userToUserDto(User user);

    @Mapping(source="userId", target = "id")
    @Mapping(source = "userFirstName", target = "firstName")
    @Mapping(source = "userLastName", target = "lastName")
    @Mapping(source = "userEmail", target = "email")
    @Mapping(target = "type", source = "userType")
    @Mapping(target = "street", source = "userAddress.addressStreet")
    @Mapping(target = "city", source = "userAddress.addressCity")
    @Mapping(target = "cityCode", source = "userAddress.addressCityCode")
    @Mapping(target = "state", source = "userAddress.addressState")
    UserWithAddressDto userToUserWithAddressDto(User user);

    @Mapping(source = "firstName", target = "userFirstName")
    @Mapping(source = "lastName", target = "userLastName")
    @Mapping(source = "email", target = "userEmail")
    @Mapping(source = "password", target = "userPassword")
    User userCreationDtoToUser(UserCreationDto userCreationDto);

    @Mapping(source = "firstName", target = "userFirstName")
    @Mapping(source = "lastName", target = "userLastName")
    @Mapping(source = "email", target = "userEmail")
    @Mapping(target = "userAddress.addressStreet", source = "street")
    @Mapping(target = "userAddress.addressCity", source = "city")
    @Mapping(target = "userAddress.addressCityCode", source = "cityCode")
    @Mapping(target = "userAddress.addressState", source = "state")
    User userUpdateDtoToUser(UserUpdateDto userUpdateDto);


    @Mapping(source="street", target = "addressStreet")
    @Mapping(source="city", target = "addressCity")
    @Mapping(source = "cityCode", target = "addressCityCode")
    @Mapping(source = "state", target = "addressState")
    Address addressCreationWithDtoToAddress(AddressCreationDto addressCreationDto);
    
    @Mapping(source = "password", target = "userPassword")
    User userPasswordChangeDtoToUser(UserPasswordChangeDto userPasswordChangeDto);

}
