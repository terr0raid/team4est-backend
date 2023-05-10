package com.team4est.storageservice.utils;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.team4est.storageservice.dto.ResponseDto;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class StorageUtils {

  @Autowired
  BlobContainerClient blobContainerClient;

  public ResponseDto upload(MultipartFile file, String path)
    throws IOException {
    if (file == null || file.isEmpty() || file.getBytes().length == 0) {
      return ResponseDto
        .builder()
        .message("error")
        .data("file is null")
        .build();
    }
    BlobClient blob = blobContainerClient.getBlobClient(
      path + "/" + UUID.randomUUID().toString() + ".jpg"
    );
    blob.upload(file.getInputStream(), file.getSize(), true);

    return ResponseDto
      .builder()
      .message("success")
      .data(blob.getBlobUrl())
      .build();
  }

  public byte[] getFile(String fileName, String path)
    throws URISyntaxException {
    BlobClient blob = blobContainerClient.getBlobClient(
      pathConcat(path, fileName)
    );
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    blob.downloadStream(outputStream);
    final byte[] bytes = outputStream.toByteArray();
    return bytes;
  }

  // public List<String> listBlobs(String path) {
  //   PagedIterable<BlobItem> items = blobContainerClient.listBlobs();
  //   List<String> names = new ArrayList<String>();
  //   for (BlobItem item : items) {
  //     names.add(item.getName());
  //   }
  //   return names;
  // }

  public ResponseDto deleteFile(String fileName, String path) {
    BlobClient blob = blobContainerClient.getBlobClient(
      pathConcat(path, fileName)
    );
    blob.delete();
    return ResponseDto.builder().message("success").build();
  }

  private String pathConcat(String path, String fileName) {
    return path + "/" + fileName;
  }
}
