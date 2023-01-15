package com.example.onlineshop.exception;

public class CategoryNotExist extends Throwable {
    public CategoryNotExist(){
        super("Category not exist", new Throwable());
    }
}
