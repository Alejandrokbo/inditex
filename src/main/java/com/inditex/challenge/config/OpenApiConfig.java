package com.inditex.challenge.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "API DOCUMENTATION FOR INDITEX  CHALLENGE",
                version = "1.0",
                description = "This API exposes endpoints to manage prices for E-Commerce.",
                contact = @io.swagger.v3.oas.annotations.info.Contact(
                        name = "Alejandro Cabo",
                        email = "alejandro.cabo1991@gmail.com",
                        url = "https://www.linkedin.com/in/alejandrokbo/"),
                license = @io.swagger.v3.oas.annotations.info.License(
                        name = "MIT License",
                        url = "https://choosealicense.com/licenses/mit/"),
                termsOfService = "https://github.com/springdoc/springdoc-openapi-maven-plugin/blob/master/LICENSE"
        ),
        servers = {
                @io.swagger.v3.oas.annotations.servers.Server(
                        url = "${inditex.openapi.dev-url}",
                        description = "Server URL in Development environment"
                )
        }
)
@Configuration
public class OpenApiConfig implements WebMvcConfigurer {
        @Override
        public void addViewControllers(final ViewControllerRegistry registry) {
                registry.addRedirectViewController("/", "/swagger-ui.html");
                // use setStatusCode(HttpStatus.XYZ) for any custom status code if required, e.g. MOVED_PERMANENTLY
                registry.addRedirectViewController("/swagger-ui", "/swagger-ui.html");
        }
}
