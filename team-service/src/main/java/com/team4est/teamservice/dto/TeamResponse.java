package com.team4est.teamservice.dto;

import java.util.Collection;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamResponse {

  private String id;
  private String name;
  private String description;
  private Boolean status;
  private String state;
  private String leader;
  private Collection<String> members;
  private String channel;
  private Date createdAt;
  private Date updatedAt;
}
