package com.team4est.storageservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto {

  private String message;
  private Object data;
}
