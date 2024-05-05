package com.arthurlevi.msreservations.feignclients;

import com.arthurlevi.msreservations.dtos.RoomDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Component
@FeignClient(name = "ms-room", path = "/rooms")
public interface RoomFeignClient {

    @GetMapping("/{id}")
    ResponseEntity<RoomDto> findRoomById(@PathVariable UUID id);
}
