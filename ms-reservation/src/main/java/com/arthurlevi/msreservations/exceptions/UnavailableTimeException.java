package com.arthurlevi.msreservations.exceptions;

public class UnavailableTimeException extends RuntimeException{

    public UnavailableTimeException(){super("Unavailable Time");}

    public UnavailableTimeException(String message){super(message);}
}
