package com.example.onlineshop.model.entity;

import java.util.List;
import java.util.UUID;

public class UserInfo extends User{

    private UUID userInfoId;
    private String userInfoFirstName;
    private String userInfoLastName;
    private String userInfoAddress;
    private String userInfoCity;
    private Integer userInfoCityCode;
    private String userInfoState;
    private List<Order> userInfoOrders;
}
