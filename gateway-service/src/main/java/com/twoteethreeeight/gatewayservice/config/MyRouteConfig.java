package com.twoteethreeeight.gatewayservice.config;

import com.twoteethreeeight.gatewayservice.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRouteConfig {
    @Autowired
    AuthenticationFilter authenticationFilter;

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("media-service", predicateSpec ->
                        predicateSpec.path("/api/v1/schedule/**")
                                .uri("lb://scheduling-service"))
                .build();
    }
}