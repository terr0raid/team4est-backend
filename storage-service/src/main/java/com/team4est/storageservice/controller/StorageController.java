package com.team4est.storageservice.controller;

import com.team4est.storageservice.dto.ResponseDto;
import com.team4est.storageservice.service.StorageService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/blob")
@RequiredArgsConstructor
public class StorageController {

  private final StorageService storageService;

  @PostMapping("/upload-profile")
  public ResponseEntity<ResponseDto> uploadProfile(
    @RequestParam("file") MultipartFile file
  ) throws IOException {
    return ResponseEntity.ok(storageService.uploadProfile(file));
  }

  @PostMapping("/upload-file")
  public ResponseEntity<ResponseDto> uploadFile(
    @RequestParam("file") MultipartFile file
  ) throws IOException {
    return ResponseEntity.ok(storageService.uploadFile(file));
  }

  @DeleteMapping("/delete-profile")
  public ResponseEntity<ResponseDto> deleteProfile(
    @RequestParam("fileName") String fileName
  ) {
    return ResponseEntity.ok(storageService.deleteProfile(fileName));
  }

  @DeleteMapping("/delete-file")
  public ResponseEntity<ResponseDto> deleteFile(
    @RequestParam("fileName") String fileName
  ) {
    return ResponseEntity.ok(storageService.deleteFile(fileName));
  }
}
