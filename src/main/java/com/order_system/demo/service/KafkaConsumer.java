package com.order_system.demo.service;

import com.order_system.demo.utils.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

  @KafkaListener(topics = AppConstants.TOPIC_NAME, groupId = AppConstants.GROUP_ID)
  public void consume(String message){
    LOGGER.info(String.format("Message received - %s", message));
  }
}
