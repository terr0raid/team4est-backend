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
  public UserResponse createProject(UserRequest project) {
    User projectEntity = mapToUser(project);

    User savedProject = userRepository.save(projectEntity);

    return mapToUserResponse(savedProject);
  }

  @Override
  public Page<UserResponse> getProjects(Pageable pageable) {
    return userRepository.findAll(pageable).map(this::mapToUserResponse);
  }
}
