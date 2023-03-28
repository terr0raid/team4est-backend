package com.team4est.authservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshResponse {

  private String accessToken;
  private String refreshToken;
  private String tokenType;
  private Long expiresIn;
  private Long expiresAt;
}
