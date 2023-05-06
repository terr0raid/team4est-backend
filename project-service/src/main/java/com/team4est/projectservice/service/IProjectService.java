package com.team4est.projectservice.service;

import com.team4est.projectservice.dto.ProjectRequest;
import com.team4est.projectservice.dto.ProjectResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProjectService {
  ProjectResponse createProject(ProjectRequest project);
  Page<ProjectResponse> getProjects(Pageable pageable);
}
