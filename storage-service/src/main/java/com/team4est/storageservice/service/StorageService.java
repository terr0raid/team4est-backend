package com.team4est.storageservice.service;

import com.team4est.storageservice.dto.ResponseDto;
import com.team4est.storageservice.model.EContainer;
import com.team4est.storageservice.utils.StorageUtils;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class StorageService {

  private final StorageUtils storageUtils;
  private final String profileContainer = "team4estcontainer/profiles";
  private final String fileContainer = "team4estcontainer/files";
  private final List<String> profileAllows = List.of(
    "image/jpeg",
    "image/png",
    "image/gif"
  );

  public ResponseDto uploadFile(MultipartFile file, EContainer path)
    throws IOException {
    switch (path) {
      case PROFILE:
        if (
          file.getContentType() == null ||
          !profileAllows.contains(file.getContentType()) ||
          file.isEmpty()
        ) return ResponseDto
          .builder()
          .message("error")
          .data("file is null or not allowed")
          .build();
        return storageUtils.upload(file, profileContainer);
      default:
        if (file.getContentType() == null || file.isEmpty()) return ResponseDto
          .builder()
          .message("error")
          .data("file is null or not allowed")
          .build();
        return storageUtils.upload(file, fileContainer);
    }
  }

  public ResponseDto deleteFile(String fileName, EContainer path) {
    switch (path) {
      case PROFILE:
        return storageUtils.deleteFile(fileName, profileContainer);
      default:
        return storageUtils.deleteFile(fileName, fileContainer);
    }
  }
}
