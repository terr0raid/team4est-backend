package com.team4est.projectservice.service;

import com.team4est.projectservice.dto.ProjectRequest;
import com.team4est.projectservice.dto.ProjectResponse;
import com.team4est.projectservice.entity.Project;
import com.team4est.projectservice.repository.ProjectRepository;
import com.team4est.projectservice.utils.ProjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService extends ProjectUtils implements IProjectService {

  private final ProjectRepository projectRepository;

  @Override
  public ProjectResponse createProject(ProjectRequest project) {
    Project projectEntity = mapToProject(project);

    Project savedProject = projectRepository.save(projectEntity);

    return mapToProjectResponse(savedProject);
  }

  @Override
  public Page<ProjectResponse> getProjects(Pageable pageable) {
    return projectRepository.findAll(pageable).map(this::mapToProjectResponse);
  }
}
