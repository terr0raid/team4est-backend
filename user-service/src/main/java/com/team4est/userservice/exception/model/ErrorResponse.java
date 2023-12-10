package com.team4est.userservice.exception.model;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

  private String message;
  private int code;
  private String path;
  private Date timestamp;
}
