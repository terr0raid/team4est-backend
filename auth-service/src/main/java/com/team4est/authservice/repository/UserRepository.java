package com.team4est.authservice.repository;

import com.team4est.authservice.entity.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Account, Long> {
  Optional<Account> findByEmail(String email);
  Optional<Account> findByUsername(String username);
  Optional<Account> findByUsernameOrEmail(String username, String email);

  Boolean existsByEmail(String email);
  Boolean existsByUsername(String username);
}
