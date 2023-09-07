package com.order_system.demo.controller;

import com.order_system.demo.service.OrderReceiveKafkaProducer;
import com.order_system.demo.service.PaymentConfirmationKafkaProducer;
import com.order_system.demo.dto.OrderDto;
import com.order_system.demo.dto.PaymentDto;
import com.order_system.demo.dto.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "api/kafka")
public class KafkaProducerController {
  private OrderReceiveKafkaProducer orderReceiveKafkaProducer;
  private PaymentConfirmationKafkaProducer paymentConfirmationKafkaProducer;

  @Autowired
  public KafkaProducerController(OrderReceiveKafkaProducer orderReceiveKafkaProducer,
                                 PaymentConfirmationKafkaProducer paymentConfirmationKafkaProducer) {
    this.orderReceiveKafkaProducer = orderReceiveKafkaProducer;
    this.paymentConfirmationKafkaProducer = paymentConfirmationKafkaProducer;
  }

  @GetMapping(path = "/publish")
//  http://localhost:8080/api/kafka/publish
  public ResponseEntity<String> publish() {
    sendOrderMessage();
    return ResponseEntity.ok("Message sent to the kafka topic");
  }

  public void sendOrderMessage() {
    OrderDto orderDto = new OrderDto();
    orderDto.setId(200L);
    orderDto.setUserName("Zira Loo");
    List<TicketDto> tickets = Arrays.asList(new TicketDto(15, "Jasmin", LocalDateTime.of(2023, 10, 12, 17, 30)),
              new TicketDto(17, "Jasmin", LocalDateTime.of(2023, 12, 10, 17, 30)));
    orderDto.setTickets(tickets);
    orderReceiveKafkaProducer.sendOrderConfirmationMessage(orderDto);
  }

  @GetMapping(path = "/payment/confirmation")
//  http://localhost:8080/api/kafka/payment/confirmation
  public ResponseEntity<String> confirmPayment() {
    sendPaymentConfirmationMessage();
    return ResponseEntity.ok("Message sent to the kafka topic");
  }

  public void sendPaymentConfirmationMessage() {
    PaymentDto dto = new PaymentDto();
    dto.setOrderId(112L);
    dto.setDate(LocalDateTime.now());
    dto.setTotal(BigDecimal.valueOf(231));

    paymentConfirmationKafkaProducer.sendPaymentConfirmation(dto);
  }
}
