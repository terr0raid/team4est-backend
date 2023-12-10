package com.team4est.userservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team4est.userservice.entity.User;
import com.team4est.userservice.events.AccountCreatedEvent;
import com.team4est.userservice.repository.UserRepository;
import java.util.Date;
import java.util.UUID;
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
  public void handleAccountCreatedEvent(ConsumerRecord<String, String> event)
    throws JsonMappingException, JsonProcessingException {
    AccountCreatedEvent account = mapper.readValue(
      event.value(),
      AccountCreatedEvent.class
    );

    UUID uuid = UUID.randomUUID();
    String randomUsername = uuid.toString().split("-")[0];

    User user = User
      .builder()
      .id(account.getId())
      .email(account.getEmail())
      .username(randomUsername)
      .bio("")
      .birthdate(new Date(System.currentTimeMillis()).toString())
      .phone("")
      .profile(
        "https://team4est.blob.core.windows.net/team4estcontainer/profiles/default-profile.jpg"
      )
      .updatedAt(new Date(System.currentTimeMillis()))
      .createdAt(new Date(System.currentTimeMillis()))
      .build();

    userRepository.save(user);
  }
}
