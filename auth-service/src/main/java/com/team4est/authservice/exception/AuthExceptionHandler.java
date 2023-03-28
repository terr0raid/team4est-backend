package com.team4est.authservice.exception;

import com.team4est.authservice.exception.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class AuthExceptionHandler {

  @ExceptionHandler({ EntityNotFoundException.class })
  public ResponseEntity<?> handleEntityNotFoundException(
    EntityNotFoundException e
  ) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }
  // @ExceptionHandler({ Exception.class })
  // public ResponseEntity<?> handleException(Exception e) {
  //   return ResponseEntity
  //     .badRequest()
  //     .body(
  //       e.getMessage() +
  //       " " +
  //       e.getCause() +
  //       " " +
  //       e.getStackTrace() +
  //       " " +
  //       e.getLocalizedMessage() +
  //       " " +
  //       e.getClass() +
  //       " " +
  //       e.getSuppressed()
  //     );
  // }
}
