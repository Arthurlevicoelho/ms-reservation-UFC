package com.arthurlevi.msroom.models;

import com.arthurlevi.msroom.enums.StatusRoom;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_ROOMS")
public class RoomModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;

    private Integer number;
    private Integer capacity;
    private StatusRoom statusRoom;

    public RoomModel(){}

    public RoomModel(UUID id, String name, Integer number, Integer capacity, StatusRoom statusRoom) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.capacity = capacity;
        this.statusRoom = statusRoom;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomModel roomModel)) return false;
        return Objects.equals(getId(), roomModel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
