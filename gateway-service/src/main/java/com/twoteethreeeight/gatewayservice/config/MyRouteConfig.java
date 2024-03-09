package com.twoteethreeeight.gatewayservice.config;

import com.twoteethreeeight.gatewayservice.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Configuration;

import static org.springframework.cloud.gateway.route.builder.RouteDslKt.filters;

@Configuration
public class MyRouteConfig {
    @Autowired
    AuthenticationFilter authenticationFilter;

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("scheduling-service", predicateSpec ->
                        predicateSpec.path("/api/v1/schedule/**")
                                .filters(f-> f.rewritePath("/api/v1/schedule//(?<segment>.*)", "//${segment}"))
                                .uri("lb://scheduling-service"))
                .route("user-service", predicateSpec ->
                        predicateSpec.path("/api/v1/user/**")
                                .filters(f -> f
                                        .rewritePath("/api/v1/user/send//(?<segment>.*)", "//${segment}")
                                        .rewritePath("/api/v1/user//(?<segment>.*)", "//${segment}")
                                        .filter(authenticationFilter))
                                .uri("lb://user-service"))
                .build();
    }
}
