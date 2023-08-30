package com.order_system.demo.service;

import com.order_system.demo.utils.AppConstants;
import dto.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducer {
  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
  private final KafkaTemplate<String,OrderDto>kafkaTemplate;

  @Autowired
  public KafkaProducer(KafkaTemplate<String, OrderDto> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendOrderConfirmationMessage(OrderDto dto){
    LOGGER.info(String.format("Message - %s - sent", dto.toString()));
    kafkaTemplate.send(AppConstants.ORDER_CREATION_TOPIC, dto);
  }
}
