package com.team4est.userservice.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@Builder
public class User {

  @Id
  private String id;

  private String username;
  private String email;
  private String profile;
  private String phone;
  private Date birthdate;
  private String bio;

  private Date createdAt;
  private Date updatedAt;
}
