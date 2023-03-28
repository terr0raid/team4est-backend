package com.team4est.projectservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Table(name = "_projects")
@Entity
@Data
@Builder
public class Project {

  @Id
  @GeneratedValue
  private Long id;

  private String title;
  private String description;
  private String status;
  private String owner;
  private String team;
  private String category;
  private String subCategory;
  private List<String> tags;
  private String startDate;
  private String endDate;
  private String createdAt;
  private String updatedAt;
}
