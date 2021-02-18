package com.griesba.brewery.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!local-discovery")
@Configuration
public class LocalhostRootConfig {
    @Bean
    RouteLocator localhostRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("beer-service", r -> r.path("/api/v1/beer*", "/api/v1/beer/*", "/api/vi/beerUpc/*")//any path matching "/api/v1/beer/*" and "/api/vi/beerUpc/*"
                        .uri("http://localhost:8080"))
                .route("beer-order-service", r -> r.path("/api/v1/customers/**")
                        .uri("http://localhost:8081"))
                .route("inventory-service", r -> r.path("/api/v1/beer/*/inventory")
                        .uri("http://localhost:8082"))
                .build();
    }
}
