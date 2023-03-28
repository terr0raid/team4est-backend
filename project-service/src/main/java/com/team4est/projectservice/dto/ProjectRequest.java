package com.team4est.projectservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectRequest {

  private String title;
  private String description;
  private String status;
  private String owner;
  private String team;
  private String category;
  private String tags;
}
