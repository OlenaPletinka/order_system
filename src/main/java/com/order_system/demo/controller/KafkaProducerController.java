package com.order_system.demo.controller;

import com.order_system.demo.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/kafka")
public class KafkaProducerController {
  private KafkaProducer kafkaProducer;

  @Autowired
  public KafkaProducerController(KafkaProducer kafkaProducer) {
    this.kafkaProducer = kafkaProducer;
  }

  @GetMapping(path = "/publish")
  public ResponseEntity<String>publish(@RequestParam("message") String message){
    kafkaProducer.sendMessage(message);
    return ResponseEntity.ok("Message sent to the kafka topic");
  }
}
