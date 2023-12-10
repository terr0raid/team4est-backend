package com.team4est.userservice.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountCreatedEvent {

  @JsonProperty("id")
  private String id;

  @JsonProperty("email")
  private String email;
}
