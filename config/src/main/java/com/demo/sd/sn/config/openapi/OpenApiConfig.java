package com.demo.sd.sn.config.openapi;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customerProductsOpenAPI(
            @Value("${spring.application.version}") String appVersion,
            @Value("${spring.application.name}") String title,
            @Value("${spring.application.description}") String description
    ) {
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .version(appVersion)
                        .description(description + """
                                <br><br>
                                <b>Corporate Headers:</b><br>
                                - <code>Authorization</code> (required)<br>
                                - <code>Application-Id</code> (required)<br>
                                <br><br>
                                - <code>Accept-Language</code> (optional)<br>
                                - <code>Correlation-Id</code> (optional)
                                """)
                        .contact(new Contact()
                                .name("Integration Team Demo")
                                .email("devops@demo.com")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Local Environment"),
                        new Server()
                                .url("https://labs.demo.com")
                                .description("Development Environment")
                ))
                .addSecurityItem(new SecurityRequirement()
                        .addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("Authorization")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("JWT Bearer Token for Microservices Authentication"))
                        .addParameters("ApplicationIdHeader",
                                requiredHeader("Application-Id", "APP-CLI-001", "Client application identifier"))
                        .addParameters("AcceptLanguageHeader",
                                optionalHeader("Accept-Language", "es", "Response language"))
                        .addParameters("CorrelationIdHeader",
                                optionalHeader("Correlation-Id", "550e8400-e29b-41d4-a716-446655440000", "Correlation identifier"))
                );
    }

    private Parameter requiredHeader(String name,
                                     String example,
                                     String description) {
        return new Parameter()
                .in(ParameterIn.HEADER.toString())
                .required(true)
                .name(name)
                .example(example)
                .description(description);
    }

    private Parameter optionalHeader(String name,
                                     String example,
                                     String description) {
        return new Parameter()
                .in(ParameterIn.HEADER.toString())
                .required(false)
                .name(name)
                .example(example)
                .description(description);
    }
}
