package com.team4est.apigateway.config;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RefreshScope
@Component
public class AuthenticationFilter
  extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

  @Autowired
  private RouterValidator routerValidator;

  @Autowired
  private JwtUtil jwtUtil;

  public AuthenticationFilter() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (
      (exchange, chain) -> {
        ServerHttpRequest request = exchange.getRequest();
        if (routerValidator.isSecured.test(request)) {
          if (this.isAuthMissing(request)) return onError(
            exchange,
            "Authorization header is missing in request",
            HttpStatus.UNAUTHORIZED
          );
          final String token = this.getAuthHeader(request);
          if (jwtUtil.isInvalid(token)) return onError(
            exchange,
            "Authorization header is invalid",
            HttpStatus.UNAUTHORIZED
          );
          this.populateRequestWithHeaders(exchange, token);
        }
        return chain.filter(exchange);
      }
    );
  }

  public static class Config {}

  private Mono<Void> onError(
    ServerWebExchange exchange,
    String err,
    HttpStatus httpStatus
  ) {
    ServerHttpResponse response = exchange.getResponse();
    response.setStatusCode(httpStatus);
    return response.setComplete();
  }

  private String getAuthHeader(ServerHttpRequest request) {
    return request.getHeaders().getOrEmpty("Authorization").get(0);
  }

  private boolean isAuthMissing(ServerHttpRequest request) {
    return !request.getHeaders().containsKey("Authorization");
  }

  private void populateRequestWithHeaders(
    ServerWebExchange exchange,
    String token
  ) {
    Claims claims = jwtUtil.getAllClaimsFromToken(token);
    exchange
      .getRequest()
      .mutate()
      .header("id", String.valueOf(claims.get("id")))
      .header("role", String.valueOf(claims.get("role")))
      .build();
  }
}
