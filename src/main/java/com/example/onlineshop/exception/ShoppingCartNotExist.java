package com.example.onlineshop.exception;

public class ShoppingCartNotExist extends Exception{
    public ShoppingCartNotExist(){
        super("Shopping cart data not exist");
    }
}
