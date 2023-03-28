package com.team4est.categoryservice.service.impl;

import com.team4est.categoryservice.dto.CategoryRequest;
import com.team4est.categoryservice.dto.CategoryResponse;
import com.team4est.categoryservice.entity.Category;
import com.team4est.categoryservice.repository.CategoryRepository;
import com.team4est.categoryservice.service.CategoryService;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  @Override
  public CategoryResponse createCategory(CategoryRequest category) {
    Date date = new Date(System.currentTimeMillis());

    Category categoryEntity = Category
      .builder()
      .name(category.getName().toLowerCase().trim().replaceAll(" ", "-"))
      .description(category.getDescription())
      .relatedTags(category.getRelatedTags())
      .parentCategoryId(category.getParentCategoryId())
      .createdAt(date)
      .updatedAt(date)
      .build();

    Category saved = categoryRepository.save(categoryEntity);
    return CategoryResponse.builder().id(saved.getCategoryId()).build();
  }

  @Override
  public CategoryResponse updateCategory(CategoryRequest category) {
    Date date = new Date(System.currentTimeMillis());

    Category categoryEntity = categoryRepository.findByName(category.getName());
    if (categoryEntity == null) {
      log.error("Category '{}' not found", category.getName());
      return null;
    }

    categoryEntity.setDescription(category.getDescription());
    categoryEntity.setRelatedTags(category.getRelatedTags());
    categoryEntity.setParentCategoryId(category.getParentCategoryId());
    categoryEntity.setUpdatedAt(date);

    Category saved = categoryRepository.save(categoryEntity);

    return CategoryResponse.builder().id(saved.getCategoryId()).build();
  }

  @Override
  public CategoryResponse deleteCategory(CategoryRequest category) {
    Category categoryEntity = categoryRepository.findByName(category.getName());

    if (categoryEntity == null) {
      log.error("Category '{}' not found", category.getName());
      return null;
    }
    categoryRepository.delete(categoryEntity);

    return CategoryResponse
      .builder()
      .id(categoryEntity.getCategoryId())
      .build();
  }

  @Override
  public Category getCategoryById(String id) {
    return categoryRepository.findById(id).orElse(null);
  }

  @Override
  public Category getCategoryByName(String name) {
    return categoryRepository.findByName(name);
  }

  @Override
  public Category getCategoryByRelatedTags(String[] relatedTags) {
    return categoryRepository.findByRelatedTags(relatedTags);
  }

  @Override
  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }
}
