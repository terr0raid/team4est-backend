package com.team4est.teamservice.entity;

import java.util.Collection;
import java.util.Date;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "teams")
@Data
@Builder
public class Team {

  @Id
  private String id;

  private String name;

  private String leader;
  private Collection<String> members;

  private String channel;
  private String description;

  private Boolean status;
  private String state;

  private Date createdAt;
  private Date updatedAt;
}
