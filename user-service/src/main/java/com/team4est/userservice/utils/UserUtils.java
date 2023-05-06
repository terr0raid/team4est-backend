package com.team4est.userservice.utils;

import com.team4est.userservice.dto.UserRequest;
import com.team4est.userservice.dto.UserResponse;
import com.team4est.userservice.entity.User;
import java.util.Date;

public class UserUtils {

  public User mapToUser(UserRequest project) {
    long current = System.currentTimeMillis();
    Date now = new Date(current);
    return User
      .builder()
      .username(project.getUsername())
      .email(project.getEmail())
      .profile(project.getProfile())
      .phone(project.getPhone())
      .birthdate(project.getBirthdate())
      .bio(project.getBio())
      .createdAt(now)
      .updatedAt(now)
      .build();
  }

  public UserResponse mapToUserResponse(User project) {
    return UserResponse
      .builder()
      .id(project.getId())
      .username(project.getUsername())
      .email(project.getEmail())
      .profile(project.getProfile())
      .phone(project.getPhone())
      .birthdate(project.getBirthdate())
      .bio(project.getBio())
      .createdAt(project.getCreatedAt())
      .updatedAt(project.getUpdatedAt())
      .build();
  }
}
