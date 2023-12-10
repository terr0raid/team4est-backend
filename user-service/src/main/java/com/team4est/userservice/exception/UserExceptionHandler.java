package com.team4est.userservice.exception;

import com.team4est.userservice.exception.exceptions.AlreadyExistsException;
import com.team4est.userservice.exception.exceptions.BadCreadentialsException;
import com.team4est.userservice.exception.exceptions.EntityNotFoundException;
import com.team4est.userservice.exception.model.ErrorResponse;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

  @ExceptionHandler({ RuntimeException.class })
  public ResponseEntity<?> handleRuntimeException(EntityNotFoundException e) {
    return ResponseEntity
      .status(HttpStatus.NOT_FOUND)
      .body(
        ErrorResponse
          .builder()
          .message(e.getMessage())
          .code(HttpStatus.SERVICE_UNAVAILABLE.value())
          .path("api/v1/users/*")
          .timestamp(new Date())
          .build()
      );
  }

  @ExceptionHandler({ EntityNotFoundException.class })
  public ResponseEntity<?> handleEntityNotFoundException(
    EntityNotFoundException e
  ) {
    return ResponseEntity
      .status(HttpStatus.NOT_FOUND)
      .body(
        ErrorResponse
          .builder()
          .message(e.getMessage())
          .code(HttpStatus.NOT_FOUND.value())
          .path("api/v1/users/*")
          .timestamp(new Date())
          .build()
      );
  }

  @ExceptionHandler({ BadCreadentialsException.class })
  public ResponseEntity<?> handleBadCreadentialsException(
    BadCreadentialsException e
  ) {
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(
        ErrorResponse
          .builder()
          .message(e.getMessage())
          .code(HttpStatus.BAD_REQUEST.value())
          .path("api/v1/users/*")
          .timestamp(new Date())
          .build()
      );
  }

  @ExceptionHandler({ AlreadyExistsException.class })
  public ResponseEntity<?> handleAlreadyExistsException(
    AlreadyExistsException e
  ) {
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(
        ErrorResponse
          .builder()
          .message(e.getMessage())
          .code(HttpStatus.BAD_REQUEST.value())
          .path("api/v1/users/*")
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
          .path("api/v1/users/*")
          .timestamp(new Date())
          .build()
      );
  }
}
