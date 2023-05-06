package com.team4est.teamservice.utils;

import com.team4est.teamservice.dto.TeamRequest;
import com.team4est.teamservice.dto.TeamResponse;
import com.team4est.teamservice.entity.Team;
import java.util.Date;

public class TeamUtils {

  public Team mapToTeam(TeamRequest project) {
    long current = System.currentTimeMillis();
    Date now = new Date(current);
    return Team
      .builder()
      .name(project.getName())
      .description(project.getDescription())
      .status(project.getStatus())
      .state(project.getState())
      .channel(project.getChannel())
      .leader(project.getLeader())
      .members(project.getMembers())
      .createdAt(now)
      .updatedAt(now)
      .build();
  }

  public TeamResponse mapToTeamResponse(Team project) {
    return TeamResponse
      .builder()
      .id(project.getId())
      .name(project.getName())
      .description(project.getDescription())
      .status(project.getStatus())
      .state(project.getState())
      .channel(project.getChannel())
      .leader(project.getLeader())
      .members(project.getMembers())
      .createdAt(project.getCreatedAt())
      .updatedAt(project.getUpdatedAt())
      .build();
  }
}
