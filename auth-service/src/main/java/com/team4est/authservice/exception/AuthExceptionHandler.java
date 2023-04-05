package com.team4est.authservice.exception;

import com.team4est.authservice.exception.exceptions.AlreadyExistsException;
import com.team4est.authservice.exception.exceptions.BadCreadentialsException;
import com.team4est.authservice.exception.exceptions.EntityNotFoundException;
import com.team4est.authservice.exception.model.ErrorResponse;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {

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
          .path("api/v1/auth/*")
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
          .path("api/v1/auth/*")
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
          .path("api/v1/auth/*")
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
          .path("api/v1/auth/*")
          .timestamp(new Date())
          .build()
      );
  }
}
