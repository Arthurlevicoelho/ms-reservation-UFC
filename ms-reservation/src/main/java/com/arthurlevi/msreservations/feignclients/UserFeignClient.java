package com.arthurlevi.msreservations.feignclients;

import com.arthurlevi.msreservations.dtos.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Component
@FeignClient(name = "ms-user",path = "/users")
public interface UserFeignClient {

    @GetMapping("/{id}")
    ResponseEntity<UserDto> findUserById(@PathVariable UUID id );
}
