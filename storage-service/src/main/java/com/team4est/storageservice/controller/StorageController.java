package com.team4est.storageservice.controller;

import com.team4est.storageservice.dto.ResponseDto;
import com.team4est.storageservice.model.EContainer;
import com.team4est.storageservice.service.StorageService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/blob")
@RequiredArgsConstructor
public class StorageController {

  private final StorageService storageService;

  @PostMapping
  public ResponseEntity<ResponseDto> uploadProfile(
    @RequestParam("file") MultipartFile file,
    @RequestParam("path") EContainer path
  ) throws IOException {
    return ResponseEntity.ok(storageService.uploadFile(file, path));
  }

  @DeleteMapping
  public ResponseEntity<ResponseDto> deleteProfile(
    @RequestParam("fileName") String fileName,
    @RequestParam("path") EContainer path
  ) {
    return ResponseEntity.ok(storageService.deleteFile(fileName, path));
  }
}
