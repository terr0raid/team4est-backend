package com.team4est.authservice.producer;

public interface IProducerService {
  void send(String topic, Object payload);
}
