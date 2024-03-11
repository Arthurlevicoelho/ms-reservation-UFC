package com.arthurlevi.msroom.exceptions;

public class FindByNameException extends RuntimeException{

    public FindByNameException(){
        super();
    }
    public FindByNameException(String message){
        super(message);
    }
}
