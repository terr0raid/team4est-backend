package com.team4est.teamservice.service;

import com.team4est.teamservice.dto.TeamRequest;
import com.team4est.teamservice.dto.TeamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITeamService {
  TeamResponse createProject(TeamRequest project);
  Page<TeamResponse> getProjects(Pageable pageable);
}
