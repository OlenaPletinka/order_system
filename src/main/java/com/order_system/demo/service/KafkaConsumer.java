package com.order_system.demo.service;

import com.order_system.demo.utils.AppConstants;
import com.order_system.demo.dto.OrderDto;
import com.order_system.demo.dto.PaymentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(OrderReceiveKafkaProducer.class);
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

  @KafkaListener(topics = AppConstants.PAYMENT_CONFIRMATION_TOPIC, groupId = AppConstants.PAYMENT_CONFIRMATION_GROUP_ID)
  public void consumePaymentMessage(PaymentDto paymentDto){
    LOGGER.info(String.format("Message with order received - %s", paymentDto.toString()));
    messageProcessor.processPaymentConfirmationMessage(paymentDto);
  }
}
