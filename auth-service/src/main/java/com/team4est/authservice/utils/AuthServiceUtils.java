package com.team4est.authservice.utils;

import com.team4est.authservice.dto.UserRegisterRequest;
import com.team4est.authservice.entity.ERole;
import com.team4est.authservice.entity.Role;
import com.team4est.authservice.entity.Token;
import com.team4est.authservice.entity.User;
import com.team4est.authservice.repository.RoleRepository;
import com.team4est.authservice.repository.TokenRepository;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthServiceUtils {

  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final RoleRepository roleRepository;

  public void saveUserToken(User user, String jwtToken, String refreshToken) {
    Date now = new Date(System.currentTimeMillis());
    Date expiresAt = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
    var token = Token
      .builder()
      .user(user)
      .accessToken(jwtToken)
      .refreshToken(refreshToken)
      .createdAt(now)
      .updatedAt(now)
      .expiresAt(expiresAt)
      .build();
    tokenRepository.save(token);
  }

  public void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty()) return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
      token.setUpdatedAt(new Date(System.currentTimeMillis()));
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public User mapToUser(UserRegisterRequest request) {
    Set<Role> roles = setRoles(request.getRoles());
    Date now = new Date(System.currentTimeMillis());
    return User
      .builder()
      .email(request.getEmail())
      .password(passwordEncoder.encode(request.getPassword()))
      .username(request.getUsername())
      .roles(roles)
      .createdAt(now)
      .updatedAt(now)
      .build();
  }

  public Set<Role> setRoles(Collection<ERole> roles) {
    if (roles == null || roles.isEmpty()) return Set.of(
      roleRepository
        .findByName(ERole.ROLE_USER.name())
        .orElseThrow(() -> new RuntimeException("Error: Role is not found."))
    );
    return roles
      .stream()
      .map(role -> {
        switch (role) {
          case ROLE_ADMIN:
            Role adminRole = roleRepository
              .findByName(ERole.ROLE_ADMIN.name())
              .orElseThrow(() ->
                new RuntimeException("Error: Role is not found.")
              );
            return adminRole;
          case ROLE_MODERATOR:
            Role modRole = roleRepository
              .findByName(ERole.ROLE_MODERATOR.name())
              .orElseThrow(() ->
                new RuntimeException("Error: Role is not found.")
              );
            return modRole;
          default:
            Role userRole = roleRepository
              .findByName(ERole.ROLE_USER.name())
              .orElseThrow(() ->
                new RuntimeException("Error: Role is not found.")
              );
            return userRole;
        }
      })
      .collect(Collectors.toSet());
  }

  public Map<String, Object> userClaims(User user) {
    Map<String, Object> claims = new HashMap<>();

    claims.put("id", user.getId());
    claims.put("username", user.getUsername());
    claims.put("email", user.getEmail());
    claims.put(
      "roles",
      user.getRoles().stream().map(Role::getName).collect(Collectors.toList())
    );
    return claims;
  }

  public User getUserByRefreshToken(String refreshToken) {
    User user = tokenRepository
      .findByRefreshToken(refreshToken)
      .map(Token::getUser)
      .orElseThrow(() -> new RuntimeException("Error: User not found."));

    return user;
  }
}
