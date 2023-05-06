package com.team4est.teamservice.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamRequest {

  private String name;
  private String description;
  private Boolean status;
  private String state;
  private String leader;

  private List<String> members;
  private String channel;
}
