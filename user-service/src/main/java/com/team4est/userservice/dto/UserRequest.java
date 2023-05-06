package com.team4est.userservice.dto;

import java.sql.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {

  private String username;
  private String email;
  private String profile;
  private String phone;
  private Date birthdate;
  private String bio;
}
