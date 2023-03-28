package com.team4est.authservice.exception.exceptions;

public class UsernameExistsException extends RuntimeException {

  public UsernameExistsException(String message) {
    super(message);
  }
}
