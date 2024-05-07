package com.arthurlevi.msgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration{

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder){

        return builder.routes().
                route("ms-user",p -> p.path("/ms-user/**")
                        .filters( f-> f.stripPrefix(1).circuitBreaker( config -> config.setName("fallBack").setFallbackUri("forward:/fallBack/UserFallBack")))
                        .uri("lb://MS-USER"))
                .route("ms-room", p -> p.path("/ms-room/**")
                        .filters( f -> f.stripPrefix(1).circuitBreaker( config -> config.setName("fallBack").setFallbackUri("forward:/fallBack/UserFallBack")))
                        .uri("lb://MS-ROOM"))
                .route("ms-reservation", p-> p.path("/ms-reservation/**")
                        .filters( f -> f.stripPrefix(1).circuitBreaker( config -> config.setName("fallBack").setFallbackUri("forward:/fallBack/UserFallBack")))
                        .uri("lb://MS-RESERVATION"))
                .build();
    }
}