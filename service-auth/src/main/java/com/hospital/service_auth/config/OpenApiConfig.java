package com.hospital.service_auth.config;


import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

import org.springframework.context.annotation.Bean;

@Configuration
public class OpenApiConfig {

   @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(new Info().title("API Home Catalogue - Servicio de Autenticación")
        .version("1.0").description("Documentación centralizada del Software Home Catalogue"))
        //Esto es lo que solicita el Failed to Fetch
        .servers(List.of(new Server().url("http://localhost:9080").description("Servidor a través del Gateway")));
    }

    

}