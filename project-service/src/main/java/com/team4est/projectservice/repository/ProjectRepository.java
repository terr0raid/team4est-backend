package com.team4est.projectservice.repository;

import com.team4est.projectservice.entity.Project;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
  Optional<Project> findByTitle(String title);
  Optional<Project> findByDescription(String desc);
  Optional<Project> findByOwner(String owner);
  Optional<Project> findByTags(List<String> tags);
}
