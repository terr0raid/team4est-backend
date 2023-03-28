package com.team4est.authservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_roles")
public class Role {

  @Id
  @GeneratedValue
  @Column(name = "role_id")
  private Long id;

  private String name;

  private String description;
}
