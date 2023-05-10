package com.team4est.authservice.service;

import com.team4est.authservice.auth.JwtService;
import com.team4est.authservice.dto.RefreshRequest;
import com.team4est.authservice.dto.RefreshResponse;
import com.team4est.authservice.dto.UserLoginRequest;
import com.team4est.authservice.dto.UserLoginResponse;
import com.team4est.authservice.dto.UserRegisterRequest;
import com.team4est.authservice.dto.UserRegisterResponse;
import com.team4est.authservice.entity.Account;
import com.team4est.authservice.entity.EToken;
import com.team4est.authservice.events.AccountCreatedEvent;
import com.team4est.authservice.exception.exceptions.AlreadyExistsException;
import com.team4est.authservice.exception.exceptions.BadCreadentialsException;
import com.team4est.authservice.exception.exceptions.EntityNotFoundException;
import com.team4est.authservice.producer.ProducerService;
import com.team4est.authservice.repository.UserRepository;
import com.team4est.authservice.utils.AuthServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final AuthServiceUtils authServiceUtils;
  private final ProducerService producerService;

  @Transactional(rollbackFor = AlreadyExistsException.class)
  @Override
  public UserRegisterResponse register(UserRegisterRequest request) {
    Account user = authServiceUtils.mapToUser(request);

    if (userRepository.existsByEmail(user.getEmail())) {
      throw new AlreadyExistsException("Email already exists");
    }
    Account savedUser = userRepository.save(user);
    String accessToken = jwtService.generateToken(
      authServiceUtils.userClaims(savedUser),
      user
    );
    String refreshToken = jwtService.generateRefreshToken();

    authServiceUtils.saveUserToken(savedUser, accessToken, refreshToken);
    try {
      producerService.send(
        "account-created",
        AccountCreatedEvent
          .builder()
          .id(savedUser.getId().toString())
          .email(savedUser.getEmail())
          .build()
      );
    } catch (Exception e) {
      throw new AlreadyExistsException(e.toString());
    }

    return UserRegisterResponse
      .builder()
      .accessToken(accessToken)
      .refreshToken(refreshToken)
      .expiresIn(3600L)
      .expiresAt(System.currentTimeMillis() + 3600L * 1000)
      .tokenType(EToken.BEARER.name())
      .build();
  }

  @Transactional
  @Override
  public UserLoginResponse login(UserLoginRequest request)
    throws EntityNotFoundException, BadCreadentialsException {
    if (request.getEmail() == null) {
      throw new BadCreadentialsException("Email is required");
    }

    Account user = authServiceUtils.authenticateUser(
      request.getEmail(),
      request.getPassword()
    );

    String accessToken = jwtService.generateToken(
      authServiceUtils.userClaims(user),
      user
    );
    String refreshToken = jwtService.generateRefreshToken();

    authServiceUtils.revokeAllUserTokens(user);
    authServiceUtils.saveUserToken(user, accessToken, refreshToken);

    return UserLoginResponse
      .builder()
      .accessToken(accessToken)
      .refreshToken(refreshToken)
      .expiresIn(3600L)
      .expiresAt(System.currentTimeMillis() + 3600L * 1000)
      .tokenType(EToken.BEARER.name())
      .build();
  }

  @Transactional
  @Override
  public RefreshResponse refresh(RefreshRequest request) {
    Account user = authServiceUtils.getUserByRefreshToken(request.getToken());

    String accessToken = jwtService.generateToken(
      authServiceUtils.userClaims(user),
      user
    );
    String newRefreshToken = jwtService.generateRefreshToken();

    authServiceUtils.revokeAllUserTokens(user);
    authServiceUtils.saveUserToken(user, accessToken, newRefreshToken);

    return RefreshResponse
      .builder()
      .accessToken(accessToken)
      .refreshToken(newRefreshToken)
      .expiresIn(3600L)
      .expiresAt(System.currentTimeMillis() + 3600L * 1000)
      .tokenType(EToken.BEARER.name())
      .build();
  }
}
