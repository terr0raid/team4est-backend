package com.team4est.projectservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Table(name = "_projects")
@Entity
@Data
@Builder
public class Project {

  @Id
  @SequenceGenerator(
    name = "project_id_seq",
    sequenceName = "project_id_seq",
    allocationSize = 1
  )
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "project_id_seq"
  )
  @Column(name = "project_id")
  private Long id;

  private String title;

  @Column(columnDefinition = "TEXT", length = 65535)
  private String description;

  private Boolean status;

  private String owner;
  private String team;
  private String category;
  private List<String> tags;
  private Date startDate;
  private Date endDate;
  private Date createdAt;
  private Date updatedAt;
}
