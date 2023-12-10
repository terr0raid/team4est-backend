package com.team4est.storageservice.exception;

import com.team4est.storageservice.exception.model.ErrorResponse;
import com.team4est.storageservice.exception.model.NotAllowedFile;
import java.io.IOException;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StorageExceptionHandler {

  @ExceptionHandler({ NotAllowedFile.class })
  public ResponseEntity<?> handleNotAllowedFileException(NotAllowedFile e) {
    return ResponseEntity
      .status(HttpStatus.NOT_FOUND)
      .body(
        ErrorResponse
          .builder()
          .message(e.getMessage())
          .code(HttpStatus.NOT_FOUND.value())
          .path("api/v1/blob/*")
          .timestamp(new Date())
          .build()
      );
  }

  @ExceptionHandler({ IOException.class })
  public ResponseEntity<?> handleIOException(IOException e) {
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(
        ErrorResponse
          .builder()
          .message(e.getMessage())
          .code(HttpStatus.BAD_REQUEST.value())
          .path("api/v1/blob/*")
          .timestamp(new Date())
          .build()
      );
  }

  @ExceptionHandler({ MethodArgumentNotValidException.class })
  public ResponseEntity<?> handleMethodArgumentNotValidException(
    MethodArgumentNotValidException e
  ) {
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(
        ErrorResponse
          .builder()
          .message(e.getMessage())
          .code(HttpStatus.BAD_REQUEST.value())
          .path("api/v1/blob/*")
          .timestamp(new Date())
          .build()
      );
  }
}
