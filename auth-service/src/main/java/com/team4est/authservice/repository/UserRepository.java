package com.team4est.authservice.repository;

import com.team4est.authservice.entity.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Account, Long> {
  Optional<Account> findByEmail(String email);

  Boolean existsByEmail(String email);
}
