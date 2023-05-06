package com.team4est.storageservice.utils;

import com.team4est.storageservice.dto.ResponseDto;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.web.multipart.MultipartFile;

public class StorageUtils {

  private final String blobPattern = "azure-blob://%s/%s";

  public ResponseDto uploadFile(
    MultipartFile file,
    ResourceLoader resourceLoader,
    String containerName
  ) throws IOException {
    Resource resource = resourceLoader.getResource(
      String.format(
        blobPattern,
        containerName,
        UUID.randomUUID().toString() + ".jpg"
      )
    );
    try (OutputStream os = ((WritableResource) resource).getOutputStream()) {
      os.write(file.getBytes());
    }
    return ResponseDto
      .builder()
      .message("success")
      .data(resource.getURL().toString())
      .build();
  }

  public ResponseDto deleteFile(
    String fileName,
    ResourceLoader resourceLoader,
    String fileContainer
  ) {
    Resource resource = resourceLoader.getResource(
      String.format(blobPattern, fileContainer, fileName)
    );
    if (resource.exists()) {
      try (OutputStream os = ((WritableResource) resource).getOutputStream()) {
        os.write(new byte[0]);
        return ResponseDto.builder().message("success").build();
      } catch (IOException e) {
        return ResponseDto
          .builder()
          .message("error")
          .data("something went wrong")
          .build();
      }
    }
    return ResponseDto
      .builder()
      .message("error")
      .data("file not found")
      .build();
  }
}
