package com.team4est.categoryservice.repository;

import com.team4est.categoryservice.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
  Category findByName(String name);

  Category findByRelatedTags(String[] relatedTags);
}
