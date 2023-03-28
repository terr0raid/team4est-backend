package com.team4est.authservice.service;

import com.team4est.authservice.dto.RefreshRequest;
import com.team4est.authservice.dto.RefreshResponse;
import com.team4est.authservice.dto.UserLoginRequest;
import com.team4est.authservice.dto.UserLoginResponse;
import com.team4est.authservice.dto.UserRegisterRequest;
import com.team4est.authservice.dto.UserRegisterResponse;

public interface IAuthenticationService {
  public UserRegisterResponse register(UserRegisterRequest request);

  public UserLoginResponse login(UserLoginRequest request);

  public RefreshResponse refresh(RefreshRequest request);
}
