package com.lgcns.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.factory.SpringCloudCircuitBreakerFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRoute (RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-route", r -> r.path("/user/**")
                        .filters(f -> f.stripPrefix(1)
                                .circuitBreaker(c -> c.setName("CircuitBreaker").getFallbackUri()))
                        .uri("lb://account-service"))
                .route("openapi", r -> r.path("/v3/api-docs/**")
                        .filters(f -> f.rewritePath("/v3/api-docs(?<service>.*)","/${service}/v3/api-docs"))
                        .uri("lb://gateway-service"))
                .build();
    }
}
