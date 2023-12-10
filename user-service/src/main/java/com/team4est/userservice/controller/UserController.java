package com.team4est.userservice.controller;

import com.team4est.userservice.dto.UserRequest;
import com.team4est.userservice.dto.UserResponse;
import com.team4est.userservice.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final IUserService userService;

  @PostMapping
  public ResponseEntity<UserResponse> createUser(
    @RequestBody UserRequest userRequest
  ) {
    return null;
  }

  @GetMapping
  public ResponseEntity<?> getUsers(
    Pageable pageable,
    @RequestParam(required = false, name = "searchParam") String searchParam
  ) {
    if (searchParam != null) {
      return ResponseEntity.ok(userService.getUser(searchParam));
    }
    return ResponseEntity.ok(userService.getUsers(pageable));
  }

  @PutMapping
  public ResponseEntity<UserResponse> updateUser(
    @RequestBody UserRequest userRequest
  ) {
    return ResponseEntity.ok(userService.updateUser(userRequest));
  }
}
