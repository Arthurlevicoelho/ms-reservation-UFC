package com.arthurlevi.msuser.exceptions;

public class UserPrincipalNotFoundException extends RuntimeException{

    public UserPrincipalNotFoundException(String message) {
        super(message);
    }
}
