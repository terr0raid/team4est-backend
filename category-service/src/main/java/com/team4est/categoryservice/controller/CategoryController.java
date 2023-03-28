package com.team4est.categoryservice.controller;

import com.team4est.categoryservice.dto.CategoryRequest;
import com.team4est.categoryservice.dto.CategoryResponse;
import com.team4est.categoryservice.entity.Category;
import com.team4est.categoryservice.service.impl.CategoryServiceImpl;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryServiceImpl categoryService;

  @GetMapping
  public ResponseEntity<List<Category>> getCategories() {
    return ResponseEntity.ok(categoryService.getAllCategories());
  }

  @PostMapping
  public ResponseEntity<CategoryResponse> createCategory(
    @RequestBody CategoryRequest category
  ) {
    CategoryResponse response = categoryService.createCategory(category);
    final URI uri = URI.create(
      ServletUriComponentsBuilder
        .fromCurrentContextPath()
        .path("/api/v1/categories")
        .toUriString()
    );
    return ResponseEntity.created(uri).body(response);
  }

  @PutMapping
  public ResponseEntity<CategoryResponse> updateCategory(
    @RequestBody CategoryRequest category
  ) {
    CategoryResponse response = categoryService.updateCategory(category);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping
  public ResponseEntity<CategoryResponse> deleteCategory(
    @RequestBody CategoryRequest category
  ) {
    CategoryResponse response = categoryService.deleteCategory(category);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Category> getCategoryById(@RequestParam String id) {
    Category category = categoryService.getCategoryById(id);
    return ResponseEntity.ok(category);
  }
}
