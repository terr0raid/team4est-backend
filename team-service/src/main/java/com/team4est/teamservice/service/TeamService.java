package com.team4est.teamservice.service;

import com.team4est.teamservice.dto.TeamRequest;
import com.team4est.teamservice.dto.TeamResponse;
import com.team4est.teamservice.entity.Team;
import com.team4est.teamservice.repository.TeamRepository;
import com.team4est.teamservice.utils.TeamUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService extends TeamUtils implements ITeamService {

  private final TeamRepository teamRepository;

  @Override
  public TeamResponse createProject(TeamRequest project) {
    Team projectEntity = mapToTeam(project);

    Team savedProject = teamRepository.save(projectEntity);

    return mapToTeamResponse(savedProject);
  }

  @Override
  public Page<TeamResponse> getProjects(Pageable pageable) {
    return teamRepository.findAll(pageable).map(this::mapToTeamResponse);
  }
}
