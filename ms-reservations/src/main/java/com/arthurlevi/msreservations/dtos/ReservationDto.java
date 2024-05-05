package com.arthurlevi.msreservations.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationDto(@JsonFormat(pattern = "dd/MM/yyyy'T'HH:mm:ss")
                             LocalDateTime startAt,
                             @JsonFormat(pattern = "dd/MM/yyyy'T'HH:mm:ss")

                             LocalDateTime endAt,
                             UUID idRoom) {
}
