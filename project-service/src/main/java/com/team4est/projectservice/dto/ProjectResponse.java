package com.team4est.projectservice.dto;

import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectResponse {

  private Long id;
  private String title;
  private String description;
  private Boolean status;
  private String owner;
  private String team;
  private String category;
  private List<String> tags;
  private Date startDate;
  private Date endDate;
  private Date createdAt;
  private Date updatedAt;
}
