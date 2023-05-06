package com.team4est.projectservice.controller;

import com.team4est.projectservice.dto.ProjectRequest;
import com.team4est.projectservice.dto.ProjectResponse;
import com.team4est.projectservice.service.IProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

  private final IProjectService projectService;

  @PostMapping
  public ResponseEntity<ProjectResponse> createProject(
    @RequestBody ProjectRequest project
  ) {
    return ResponseEntity
      .created(null)
      .body(projectService.createProject(project));
  }

  @GetMapping
  public Page<ProjectResponse> getProjects(Pageable pageable) {
    return projectService.getProjects(pageable);
  }
}
