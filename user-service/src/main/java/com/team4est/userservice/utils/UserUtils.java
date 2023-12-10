package com.team4est.userservice.utils;

import com.team4est.userservice.dto.UserRequest;
import com.team4est.userservice.dto.UserResponse;
import com.team4est.userservice.entity.User;
import java.util.Date;

public class UserUtils {

  public User mapToUser(UserRequest user) {
    long current = System.currentTimeMillis();
    Date now = new Date(current);
    return User
      .builder()
      .id(user.getId())
      .username(user.getUsername())
      .email(user.getEmail())
      .name(user.getName())
      .lastName(user.getLastName())
      .profile(user.getProfile())
      .phone(user.getPhone())
      .birthdate(user.getBirthdate())
      .bio(user.getBio())
      .updatedAt(now)
      .build();
  }

  public UserResponse mapToUserResponse(User user) {
    return UserResponse
      .builder()
      .id(user.getId())
      .username(user.getUsername())
      .name(user.getName())
      .lastName(user.getLastName())
      .email(user.getEmail())
      .profile(user.getProfile())
      .phone(user.getPhone())
      .birthdate(user.getBirthdate())
      .bio(user.getBio())
      .build();
  }
}
