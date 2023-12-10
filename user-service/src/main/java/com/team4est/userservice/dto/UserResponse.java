package com.team4est.userservice.dto;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

  private String id;

  private String username;
  private String email;
  private String name;
  private String lastName;
  private String profile;
  private String phone;
  private String birthdate;
  private String bio;
}
