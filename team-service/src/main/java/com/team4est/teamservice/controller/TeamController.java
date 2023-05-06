package com.team4est.teamservice.controller;

import com.team4est.teamservice.dto.TeamRequest;
import com.team4est.teamservice.dto.TeamResponse;
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
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
public class TeamController {

  @PostMapping
  public ResponseEntity<TeamResponse> createTeam(
    @RequestBody TeamRequest teamRequest
  ) {
    return null;
  }

  @GetMapping
  public ResponseEntity<Page<TeamResponse>> getTeams(Pageable pageable) {
    return null;
  }
}
