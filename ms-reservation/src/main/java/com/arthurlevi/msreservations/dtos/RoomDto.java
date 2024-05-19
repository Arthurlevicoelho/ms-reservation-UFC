package com.arthurlevi.msreservations.dtos;

import com.arthurlevi.msreservations.enums.StatusRoom;

import java.util.UUID;

public class RoomDto {

    private UUID id;
    private String name;

    private Integer number;
    private Integer capacity;

    private StatusRoom statusRoom;

    public RoomDto(UUID uuid, String salaDosProfessores, int number, int capacity, StatusRoom statusRoom) {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public StatusRoom getStatusRoom() {
        return statusRoom;
    }

    public void setStatusRoom(StatusRoom statusRoom) {
        this.statusRoom = statusRoom;
    }
}
