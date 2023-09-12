package com.order_system.demo.service;

import com.order_system.demo.dto.PaymentDto;
import com.order_system.demo.utils.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentConfirmationKafkaProducer {
  private static final Logger LOGGER = LoggerFactory.getLogger(PaymentConfirmationKafkaProducer.class);
  private final KafkaTemplate<String, PaymentDto> kafkaTemplate;

  @Autowired
  public PaymentConfirmationKafkaProducer(KafkaTemplate<String, PaymentDto> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendPaymentConfirmation(PaymentDto dto){
    LOGGER.info(String.format("Message - %s - sent", dto.toString()));
    kafkaTemplate.send(AppConstants.PAYMENT_CONFIRMATION_TOPIC, dto);
  }
}
