package com.team4est.authservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_tokens")
public class Token {

  @Id
  @SequenceGenerator(
    name = "token_id_seq",
    sequenceName = "token_id_seq",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "token_id_seq"
  )
  @Column(name = "token_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private Account user;

  @Column(unique = true, name = "access_token", length = 700)
  private String accessToken;

  @Column(name = "refresh_token")
  private String refreshToken;

  @Enumerated(EnumType.STRING)
  @Column(name = "token_type")
  @Default
  private EToken tokenType = EToken.BEARER;

  @Default
  private boolean revoked = false;

  @Default
  private boolean expired = false;

  @Column(name = "expires_at")
  private Date expiresAt;

  @Column(name = "created_at")
  private Date createdAt;

  @Column(name = "updated_at")
  private Date updatedAt;
}
