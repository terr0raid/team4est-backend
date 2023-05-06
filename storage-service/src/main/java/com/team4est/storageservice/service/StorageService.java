package com.team4est.storageservice.service;

import com.azure.spring.cloud.core.resource.AzureStorageBlobProtocolResolver;
import com.team4est.storageservice.dto.ResponseDto;
import com.team4est.storageservice.utils.StorageUtils;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class StorageService extends StorageUtils {

  private final String profileContainer = "team4estcontainer/profiles";
  private final String fileContainer = "team4estcontainer/files";
  private final ResourceLoader resourceLoader;

  // private final AzureStorageBlobProtocolResolver azureStorageBlobProtocolResolver;

  public ResponseDto uploadProfile(MultipartFile file) throws IOException {
    return uploadFile(file, resourceLoader, profileContainer);
  }

  public ResponseDto uploadFile(MultipartFile file) throws IOException {
    return uploadFile(file, resourceLoader, fileContainer);
  }

  public ResponseDto deleteProfile(String fileName) {
    return deleteFile(fileName, resourceLoader, profileContainer);
  }

  public ResponseDto deleteFile(String fileName) {
    return deleteFile(fileName, resourceLoader, fileContainer);
  }
}
