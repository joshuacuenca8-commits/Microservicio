package com.joshuaCuenca.microservicio.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MS Productos API")
                        .version("1.0.0")
                        .description("Microservicio para gestión de productos")
                        .contact(new Contact()
                                .name("Joshua")
                                .email("joshua@email.com")));
    }
}