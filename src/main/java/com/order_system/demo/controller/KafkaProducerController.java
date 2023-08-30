package com.order_system.demo.controller;

import com.order_system.demo.service.KafkaProducer;
import dto.OrderDto;
import dto.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "api/kafka")
public class KafkaProducerController {
  private KafkaProducer kafkaProducer;

  @Autowired
  public KafkaProducerController(KafkaProducer kafkaProducer) {
    this.kafkaProducer = kafkaProducer;
  }

  @GetMapping(path = "/publish")
//  http://localhost:8080/api/kafka/publish
  public ResponseEntity<String> publish() {
    sendOrderMessage();
    return ResponseEntity.ok("Message sent to the kafka topic");
  }

  public void sendOrderMessage() {
    OrderDto orderDto = new OrderDto();
    orderDto.setId(100L);
    orderDto.setUserName("Olena Pletinka");
    List<TicketDto> tickets = Arrays.asList(new TicketDto(12, "Ballet", LocalDateTime.of(2023, 12, 12, 12, 30)),
              new TicketDto(13, "Ballet", LocalDateTime.of(2023, 12, 12, 12, 30)));
    orderDto.setTickets(tickets);
    kafkaProducer.sendOrderConfirmationMessage(orderDto);
  }
}
