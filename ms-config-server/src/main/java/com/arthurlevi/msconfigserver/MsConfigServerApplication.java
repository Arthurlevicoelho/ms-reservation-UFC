package com.arthurlevi.msconfigserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class MsConfigServerApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(MsConfigServerApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		String usergithub =System.getenv("USERGITHUB");
		System.out.println("USERNAME=" + usergithub);
//		System.out.println("USERPASS=" + pass);
	}
}
