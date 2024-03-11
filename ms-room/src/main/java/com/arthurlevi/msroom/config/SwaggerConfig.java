package com.arthurlevi.msroom.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Controlador REST para operações relacionadas a salas")
                        .description("Este controlador gerencia operações como busca, criação, atualização e exclusão de salas.\n" +
                                "Todas as operações são acessíveis através dos respectivos endpoints.")
                        .contact(new Contact().name("ArthurCoelho").email("arthurlevi81@gmail.com")).version("1.0.0"));
    }
}