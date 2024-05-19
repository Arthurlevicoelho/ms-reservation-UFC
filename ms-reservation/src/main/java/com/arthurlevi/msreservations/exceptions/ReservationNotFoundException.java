package com.arthurlevi.msreservations.exceptions;

public class ReservationNotFoundException extends RuntimeException{

    public ReservationNotFoundException(){super();}

    public ReservationNotFoundException(String message){super(message);}
}
