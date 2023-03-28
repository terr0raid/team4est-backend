package com.team4est.apigateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

  private String secret =
    "36763979244226452948404D635166546A576E5A7134743777217A25432A462D";

  private Key key;

  @PostConstruct
  public void init() {
    this.key = Keys.hmacShaKeyFor(secret.getBytes());
  }

  public Claims getAllClaimsFromToken(String token) {
    return Jwts
      .parserBuilder()
      .setSigningKey(key)
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

  private boolean isTokenExpired(String token) {
    return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
  }

  public boolean isInvalid(String token) {
    return this.isTokenExpired(token);
  }
}
