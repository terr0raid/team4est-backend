package com.team4est.authservice.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountCreatedEvent {

  private String id;
  private String email;
}
