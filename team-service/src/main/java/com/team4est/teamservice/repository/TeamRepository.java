package com.team4est.teamservice.repository;

import com.team4est.teamservice.entity.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository<Team, String> {}
