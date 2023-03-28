package com.team4est.authservice.repository;

import com.team4est.authservice.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
  Optional<User> findByUsernameOrEmail(String username, String email);

  Boolean existsByEmail(String email);
  Boolean existsByUsername(String username);
}
