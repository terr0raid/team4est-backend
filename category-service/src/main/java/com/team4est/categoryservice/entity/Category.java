package com.team4est.categoryservice.entity;

import java.util.Collection;
import java.util.Date;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "categories")
@Builder
public class Category {

  @Id
  private String categoryId;

  private String name;
  private String description;
  private String parentCategoryId;
  private Collection<String> relatedTags;
  private Date createdAt;
  private Date updatedAt;
}
