package com.team4est.userservice.service;

import com.team4est.userservice.dto.UserRequest;
import com.team4est.userservice.dto.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
  UserResponse createUser(UserRequest project);
  Page<UserResponse> getUsers(Pageable pageable);
  UserResponse getUser(String userSearchRequest);
  UserResponse updateUser(UserRequest userRequest);
}
