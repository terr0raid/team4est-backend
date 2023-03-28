package com.team4est.categoryservice.service;

import com.team4est.categoryservice.dto.CategoryRequest;
import com.team4est.categoryservice.dto.CategoryResponse;
import com.team4est.categoryservice.entity.Category;
import java.util.List;

public interface CategoryService {
  List<Category> getAllCategories();
  CategoryResponse createCategory(CategoryRequest category);
  CategoryResponse updateCategory(CategoryRequest category);
  CategoryResponse deleteCategory(CategoryRequest category);
  Category getCategoryById(String id);
  Category getCategoryByName(String name);
  Category getCategoryByRelatedTags(String[] relatedTags);
}
