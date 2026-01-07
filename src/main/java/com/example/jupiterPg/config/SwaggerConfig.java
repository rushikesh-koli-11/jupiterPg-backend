package com.example.jupiterPg.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI jupiterPgOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Jupiter PG Management API")
                        .description("REST APIs for Jupiter PG - PG Management & Booking Platform")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Jupiter PG Team")
                                .email("support@jupiterpg.com")
                                .url("https://jupiterpg.com")
                        )
                );
    }
}
