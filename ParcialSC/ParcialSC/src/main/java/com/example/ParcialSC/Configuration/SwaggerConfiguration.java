package com.example.ParcialSC.Configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI swaggerConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Ventas")
                        .version("1.0")
                        .description("Documentación de la API de ventas para Línea de Profundización III")
                        .contact(new Contact()
                                .name("Sergio Daniel Castellanos Rodriguez ")
                                .email("sdcastellanos@ucundinamarca.edu.co")));
    }
}