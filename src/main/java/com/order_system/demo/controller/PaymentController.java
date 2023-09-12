package com.order_system.demo.controller;

import com.order_system.demo.dto.PaymentDto;
import com.order_system.demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payment")
public class PaymentController {
  private final PaymentService paymentService;

  @Autowired
  public PaymentController(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

//  http://localhost:8080/payment/receive
//  {
//    "orderId" : 100,
//    "date" : "2023-09-01 10:08:02",
//    "total" : 521
//}
  @PostMapping(path = "/receive")
  public ResponseEntity<String> receivePayment(@RequestBody PaymentDto dto){
    Long id = paymentService.receivePayment(dto);
    return ResponseEntity.ok(String.format("Payment confirmation with id - %d was received", id));

  }
}
