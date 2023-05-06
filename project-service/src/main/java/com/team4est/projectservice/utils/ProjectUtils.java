package com.team4est.projectservice.utils;

import com.team4est.projectservice.dto.ProjectRequest;
import com.team4est.projectservice.dto.ProjectResponse;
import com.team4est.projectservice.entity.Project;
import java.util.Date;

public class ProjectUtils {

  public Project mapToProject(ProjectRequest project) {
    long current = System.currentTimeMillis();
    Date now = new Date(current);
    return Project
      .builder()
      .title(project.getTitle())
      .description(project.getDescription())
      .status(project.getStatus())
      .owner(project.getOwner())
      .team(project.getTeam())
      .category(project.getCategory())
      .tags(project.getTags())
      .startDate(now)
      .endDate(new Date(current + 1000 * 60 * 60 * 24 * 15))
      .createdAt(now)
      .updatedAt(now)
      .build();
  }

  public ProjectResponse mapToProjectResponse(Project project) {
    return ProjectResponse
      .builder()
      .id(project.getId())
      .title(project.getTitle())
      .description(project.getDescription())
      .status(project.getStatus())
      .owner(project.getOwner())
      .team(project.getTeam())
      .category(project.getCategory())
      .tags(project.getTags())
      .startDate(project.getStartDate())
      .endDate(project.getEndDate())
      .createdAt(project.getCreatedAt())
      .updatedAt(project.getUpdatedAt())
      .build();
  }
}
