package com.order_system.demo.service;

import com.order_system.demo.dto.ReservationDto;
import com.order_system.demo.utils.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationKafkaProducer {
  private static final Logger LOGGER = LoggerFactory.getLogger(ReservationKafkaProducer.class);
  private final KafkaTemplate<String, List<ReservationDto>> kafkaTemplate;

  @Autowired
  public ReservationKafkaProducer(KafkaTemplate<String, List<ReservationDto>> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessageToReservation(List<ReservationDto> dtos){
    kafkaTemplate.send(AppConstants.RESERVATION_CREATION_TOPIC, dtos);
  }
}
