package com.arthurlevi.msuser.exceptions;

public class CreateUserException extends RuntimeException{

    public CreateUserException(){
        super();
    }

    public CreateUserException(String message){
        super(message);
    }
}
