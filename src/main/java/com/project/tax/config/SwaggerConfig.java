package com.project.tax.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(title = "tax Service API",
        description = "세금 API 규격서",
        version = "v1"))
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "bearerAuth", scheme = "bearer")
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "basicAuth", scheme = "basic", description = "username: tax_web\n\npassword: tax_web\n\nex) Basic ZHVvdG9uZV93ZWI6ZHVvdG9uZV93ZWI=")
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
