package com.team4est.userservice;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team4est.userservice.entity.User;
import com.team4est.userservice.events.AccountCreatedEvent;
import com.team4est.userservice.repository.UserRepository;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableDiscoveryClient
@RequiredArgsConstructor
public class UserServiceApplication {

  private final ObjectMapper mapper;
  private final UserRepository userRepository;

  public static void main(String[] args) {
    SpringApplication.run(UserServiceApplication.class, args);
  }

  @KafkaListener(topics = "account-created", groupId = "account")
  public void handleAccountCreatedEvent(ConsumerRecord<String, Object> event) {
    mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

    AccountCreatedEvent account = mapper.convertValue(
      event.value(),
      AccountCreatedEvent.class
    );

    User user = User
      .builder()
      .id(account.getId())
      .email(account.getEmail())
      .bio("")
      .birthdate(new Date(System.currentTimeMillis()))
      .phone("")
      .profile("")
      .updatedAt(new Date(System.currentTimeMillis()))
      .createdAt(new Date(System.currentTimeMillis()))
      .build();

    userRepository.save(user);
  }
}
