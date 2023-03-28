package com.team4est.apigateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

  AuthenticationFilter filter;

  @Bean
  RouteLocator routeLocator(
    RouteLocatorBuilder rlb,
    AuthenticationFilter authenticationFilter
  ) {
    return rlb
      .routes()
      .route(p ->
        p
          .path("/api/v1/categories/**")
          .filters(f ->
            f
              .removeRequestHeader("Cookie")
              .filter(
                authenticationFilter.apply(new AuthenticationFilter.Config())
              )
          )
          .uri("lb://category-service")
      )
      .route(p ->
        p
          .path("/api/v1/auth/**")
          .filters(f ->
            f
              .removeRequestHeader("Cookie")
              .filter(
                authenticationFilter.apply(new AuthenticationFilter.Config())
              )
          )
          .uri("lb://auth-service")
      )
      .build();
  }
}
