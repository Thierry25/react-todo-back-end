package com.thierry.marcelin.restfulservices.exceptions;

public class TodoNotFound extends RuntimeException{
    public TodoNotFound(String message){
        super(message);
    }
}
