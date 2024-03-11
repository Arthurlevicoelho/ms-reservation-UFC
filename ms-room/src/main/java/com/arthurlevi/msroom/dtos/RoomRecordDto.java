package com.arthurlevi.msroom.dtos;

import com.arthurlevi.msroom.enums.StatusRoom;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoomRecordDto(@NotBlank(message = "Name nao pode ser nulo") String name,
                            @NotNull(message = "Number not null") Integer number,
                            @NotNull(message = "Capacity not null") Integer capacity) {
}
