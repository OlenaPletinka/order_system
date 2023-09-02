package com.order_system.demo.service;

import com.order_system.demo.entity.Payment;
import dto.PaymentDto;

public interface PaymentService {
  Long receivePayment(PaymentDto dto);

}
