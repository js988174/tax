package com.project.tax.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(title = "tax Service API",
        description = "세금 API 규격서",
        version = "v1"))
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi chatOpenApi() {
        String[] paths = {"/1.0/**"};
        return GroupedOpenApi.builder()
            .group("tax API v1")
            .pathsToMatch(paths)
            .build();
    }

}
