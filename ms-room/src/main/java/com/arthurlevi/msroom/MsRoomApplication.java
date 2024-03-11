package com.arthurlevi.msroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsRoomApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsRoomApplication.class, args);
	}

}
