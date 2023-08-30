package com.order_system.demo.service;

import com.order_system.demo.utils.AppConstants;
import dto.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
  private final MessageProcessor messageProcessor;

  @Autowired
  public KafkaConsumer(MessageProcessor messageProcessor) {
    this.messageProcessor = messageProcessor;
  }

  @KafkaListener(topics = AppConstants.ORDER_CREATION_TOPIC, groupId = AppConstants.ORDER_RECEIVED_GROUP_ID)
  public void consumeOrderMessage(OrderDto dto) {
    LOGGER.info(String.format("Message with order received - %s", dto.toString()));
    messageProcessor.processOrderReceivingMessage(dto);
  }
}
