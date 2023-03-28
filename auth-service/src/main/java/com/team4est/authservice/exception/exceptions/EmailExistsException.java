package com.team4est.authservice.exception.exceptions;

public class EmailExistsException extends RuntimeException {

  public EmailExistsException(String message) {
    super(message);
  }
}
