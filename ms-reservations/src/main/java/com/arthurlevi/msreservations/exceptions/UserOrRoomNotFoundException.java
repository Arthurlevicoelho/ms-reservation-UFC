package com.arthurlevi.msreservations.exceptions;

public class UserOrRoomNotFoundException extends RuntimeException {

    public UserOrRoomNotFoundException(){super("User or Room not found");}

    public UserOrRoomNotFoundException(String message){super(message);}
}
