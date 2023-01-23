package com.example.onlineshop.exception;

public class NotEnoughtStock extends Exception{
    public NotEnoughtStock(){
        super("Not enought stock for product");
    }
}
