package com.arthurlevi.msreservations.dtos;

import java.util.UUID;

public class UserDto {

    private UUID id;
    private String email;
    private Integer registration;

    public UserDto(UUID uuid, String mail, String number, Integer registration) {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRegistration() {
        return registration;
    }

    public void setRegistration(Integer registration) {
        this.registration = registration;
    }
}
