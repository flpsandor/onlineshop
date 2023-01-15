package com.example.onlineshop.exception;

public class CategoryNotValid extends Exception{
    public CategoryNotValid(){
        super("Category not valid", new Throwable());
    }
}
