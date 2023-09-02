package com.order_system.demo.controller;

import com.order_system.demo.service.PaymentService;
import dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payment")
public class PaymentController {
  private final PaymentService paymentService;

  @Autowired
  public PaymentController(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @PostMapping(path = "/receive")
  public ResponseEntity<String> receivePayment(@RequestBody PaymentDto dto){
    Long id = paymentService.receivePayment(dto);
    return ResponseEntity.ok(String.format("Payment confirmation with id - %d was received", id));

  }
}
