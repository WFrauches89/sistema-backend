package com.meuscursos.backend.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {


    @Bean
    public OpenAPI customOpenAPI() {
        String s = "Wanderson Frauches";
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("Backend do Zero ao tudo - Com o professor Rogério Napoleão Jr")
                        .description("API Rest da aplicação Zero ao tudo, contendo as funcionalidades de CRUD")
                        .contact(new Contact()
                                .name("wfrauches89@gmail.com")
                                .email("wfrauches89@gmail.com")).description(s)
                        .license(new License()
                                .name("<- Link para o meu LinkedIn -> ")
                                .url("https://www.linkedin.com/in/wanderson-frauches/")));
    }

}
