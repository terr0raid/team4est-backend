package com.team4est.apigateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

  @Value("${app.jwt.secret}")
  private String SECRET_KEY;

  public Claims getAllClaimsFromToken(String token) {
    try {
      return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
    } catch (Exception e) {
      return null;
    }
  }

  private boolean isTokenExpired(String token) {
    if (getAllClaimsFromToken(token) == null) return true;
    return getAllClaimsFromToken(token).getExpiration().before(new Date());
  }

  public boolean isInvalid(String token) {
    return isTokenExpired(token);
  }

  private Key getSignInKey() {
    byte[] apiKeySecretBytes = SECRET_KEY.getBytes();
    return Keys.hmacShaKeyFor(apiKeySecretBytes);
  }
}
