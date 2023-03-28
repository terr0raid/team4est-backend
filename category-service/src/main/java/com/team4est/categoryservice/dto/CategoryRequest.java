package com.team4est.categoryservice.dto;

import java.util.Collection;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryRequest {

  private String name;
  private String description;
  private String parentCategoryId;
  private Collection<String> relatedTags;
}
