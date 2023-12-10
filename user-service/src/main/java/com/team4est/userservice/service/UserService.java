package com.team4est.userservice.service;

import com.team4est.userservice.dto.UserRequest;
import com.team4est.userservice.dto.UserResponse;
import com.team4est.userservice.entity.User;
import com.team4est.userservice.repository.UserRepository;
import com.team4est.userservice.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService extends UserUtils implements IUserService {

  private final UserRepository userRepository;

  @Override
  public UserResponse createUser(UserRequest userRequest) {
    User user = mapToUser(userRequest);

    User savedProject = userRepository.save(user);

    return mapToUserResponse(savedProject);
  }

  @Override
  public Page<UserResponse> getUsers(Pageable pageable) {
    return userRepository.findAll(pageable).map(this::mapToUserResponse);
  }

  @Override
  public UserResponse getUser(String searchParam) {
    User user = userRepository.findByIdOrUsernameOrEmail(searchParam);

    return mapToUserResponse(user);
  }

  @Override
  public UserResponse updateUser(UserRequest userRequest) {
    User user = userRepository
      .findById(userRequest.getId())
      .orElseThrow(() -> new RuntimeException("User not found"));

    if (user != null) {
      user = mapToUser(userRequest);

      User savedUser = userRepository.save(user);

      return mapToUserResponse(savedUser);
    }

    throw new RuntimeException("User not found");
  }
}
