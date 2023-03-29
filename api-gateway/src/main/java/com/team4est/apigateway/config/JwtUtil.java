package com.team4est.apigateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

  private String SECRET_KEY =
    "36763979244226452948404D635166546A576E5A7134743777217A25432A462D";

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
    byte[] apiKeySecretBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(apiKeySecretBytes);
  }
}
