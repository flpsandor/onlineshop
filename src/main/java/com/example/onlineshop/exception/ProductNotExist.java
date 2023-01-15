package com.example.onlineshop.exception;

public class ProductNotExist extends Exception{

    public ProductNotExist(){
        super("Product not exist", new Throwable());
    }
}
