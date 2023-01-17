package com.thierry.marcelin.restfulservices.exceptions;

public class TodoNotFound extends RuntimeException{
    private String message;
    public TodoNotFound(String message){
        super(message);
    }
}
