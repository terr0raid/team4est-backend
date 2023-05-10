package com.team4est.userservice.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountCreatedEvent {

  private String id;
  private String email;
}
