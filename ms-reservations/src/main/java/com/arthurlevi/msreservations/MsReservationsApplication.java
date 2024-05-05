package com.arthurlevi.msreservations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class MsReservationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsReservationsApplication.class, args);
	}

}
