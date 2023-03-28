package com.team4est.authservice.controller;

import com.team4est.authservice.dto.RefreshRequest;
import com.team4est.authservice.dto.RefreshResponse;
import com.team4est.authservice.dto.UserLoginRequest;
import com.team4est.authservice.dto.UserLoginResponse;
import com.team4est.authservice.dto.UserRegisterRequest;
import com.team4est.authservice.dto.UserRegisterResponse;
import com.team4est.authservice.service.AuthenticationService;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<UserRegisterResponse> register(
    @Valid @RequestBody UserRegisterRequest request
  ) {
    UserRegisterResponse user = service.register(request);
    return ResponseEntity.created(URI.create("/api/v1/users/")).body(user);
  }

  @PostMapping("/login")
  public ResponseEntity<UserLoginResponse> login(
    @Valid @RequestBody UserLoginRequest request
  ) {
    return ResponseEntity.ok(service.login(request));
  }

  @PostMapping("/refresh")
  public ResponseEntity<RefreshResponse> refresh(
    @Valid @RequestBody RefreshRequest request
  ) {
    return ResponseEntity.ok(service.refresh(request));
  }
}
