package com.arthurlevi.msgateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallBack")
public class FallBackControler {

    @RequestMapping(value = "/UserFallBack")
    public ResponseEntity<String> UserFallback(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Our system is experiencing problems, please contact us at xxxxx@gmail.com");
    }
}
