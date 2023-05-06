package com.team4est.projectservice.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectRequest {

  private String title;
  private String description;
  private Boolean status;
  private String owner;
  private String team;
  private String category;
  private List<String> tags;
}
