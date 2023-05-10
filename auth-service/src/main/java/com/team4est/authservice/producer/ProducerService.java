package com.team4est.authservice.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService implements IProducerService {

  private final KafkaTemplate<String, Object> kafkaTemplate;

  @Override
  public void send(String topic, Object payload) {
    kafkaTemplate.send(topic, payload);
  }
}
