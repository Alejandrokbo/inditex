package com.inditex.challenge.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

    @Value("${inditex.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("alejandro.cabo1991@gmail.com");
        contact.setName("alejandrokbo");
        contact.setUrl("https://www.linkedin.com/in/alejandrokbo/");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("API DOCUMENTATION FOR INDITEX CHALLENGE")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage prices for E-Commerce.")
                .termsOfService("https://github.com/springdoc/springdoc-openapi-maven-plugin/blob/master/LICENSE")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}