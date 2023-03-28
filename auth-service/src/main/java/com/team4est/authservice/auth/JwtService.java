package com.team4est.authservice.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import org.apache.commons.codec.digest.Crypt;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  private final String SECRET_KEY =
    "36763979244226452948404D635166546A576E5A7134743777217A25432A462D";

  private int expDate = 1000 * 64 * 24 * 5;

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  public String generateToken(
    Map<String, Object> claims,
    UserDetails userDetails
  ) {
    return Jwts
      .builder()
      .setClaims(claims)
      .setSubject(userDetails.getUsername())
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + expDate))
      .signWith(getSignInKey(), SignatureAlgorithm.HS256)
      .compact();
  }

  public String generateRefreshToken() {
    return UUID.randomUUID().toString();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);

    return (
      (username.equals(userDetails.getUsername())) && !isTokenExpired(token)
    );
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token)
      .before(new Date(System.currentTimeMillis()));
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
      .parserBuilder()
      .setSigningKey(getSignInKey())
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

  private Key getSignInKey() {
    byte[] apiKeySecretBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(apiKeySecretBytes);
  }
}
