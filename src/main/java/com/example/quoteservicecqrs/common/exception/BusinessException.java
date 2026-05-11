package com.example.quoteservicecqrs.common.exception;

public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super(message);
    }
}