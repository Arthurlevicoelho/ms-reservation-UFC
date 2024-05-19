package com.arthurlevi.msroom.models;

import com.arthurlevi.msroom.enums.StatusRoom;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "room",name = "rooms")
public class RoomModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotBlank(message = "Name nao pode ser nulo")
    private String name;
    @NotNull(message = "Number not null")
    private Integer number;
    @NotNull(message = "Capacity not null")
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "room.status_room_enum")
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
