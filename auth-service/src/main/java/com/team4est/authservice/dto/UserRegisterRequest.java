package com.team4est.authservice.dto;

import com.team4est.authservice.entity.ERole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

  @Size(min = 3, max = 20)
  @NotBlank
  private String username;

  @Email
  @NotBlank
  @Size(max = 50)
  private String email;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

  @Enumerated(EnumType.STRING)
  private Collection<ERole> roles;
}
