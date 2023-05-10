package com.team4est.authservice;

import com.team4est.authservice.entity.ERole;
import com.team4est.authservice.entity.Role;
import com.team4est.authservice.repository.RoleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthServiceRunner implements CommandLineRunner {

  private final RoleRepository roleRepository;

  @Override
  public void run(String... args) throws Exception {
    if (roleRepository.existsById(1L)) {
      return;
    }
    roleRepository.saveAll(
      List.of(
        Role
          .builder()
          .name(ERole.ROLE_USER.name())
          .description("userrole")
          .build(),
        Role
          .builder()
          .name(ERole.ROLE_ADMIN.name())
          .description("admin role")
          .build(),
        Role
          .builder()
          .name(ERole.ROLE_MODERATOR.name())
          .description("moderator role")
          .build()
      )
    );
  }
}
