package com.team4est.authservice.exception;

import com.team4est.authservice.exception.exceptions.AlreadyExistsException;
import com.team4est.authservice.exception.exceptions.BadCreadentialsException;
import com.team4est.authservice.exception.exceptions.EntityNotFoundException;
import com.team4est.authservice.exception.model.ErrorResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
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
          .message(regexMessage(e.getMessage()))
          .code(HttpStatus.BAD_REQUEST.value())
          .path("api/v1/auth/*")
          .timestamp(new Date())
          .build()
      );
  }

  private String regexMessage(String message) {
    final String regex = "(?<=\\[).+?(?=\\])";
    String[] split = message.split(";");

    String returnMessage = "";
    returnMessage += split[4] + split[5];

    List<String> allMatches = new ArrayList<String>();
    Matcher m = Pattern.compile(regex).matcher(returnMessage);
    while (m.find()) {
      allMatches.add(m.group());
    }

    return allMatches.get(0) + " " + allMatches.get(1);
  }
}
