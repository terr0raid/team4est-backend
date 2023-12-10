package com.team4est.storageservice.service;

import com.team4est.storageservice.dto.ResponseDto;
import com.team4est.storageservice.exception.model.NotAllowedFile;
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
        ) throw new NotAllowedFile("file is null or not allowed");
        return storageUtils.upload(file, path.getContainerName());
      default:
        if (
          file.getContentType() == null || file.isEmpty()
        ) throw new NotAllowedFile("file is null or not allowed");
        return storageUtils.upload(file, path.getContainerName());
    }
  }

  public ResponseDto deleteFile(String fileName, EContainer path) {
    switch (path) {
      case PROFILE:
        return storageUtils.deleteFile(fileName, path.getContainerName());
      default:
        return storageUtils.deleteFile(fileName, path.getContainerName());
    }
  }
}
