package com.order_system.demo.service;

import com.order_system.demo.dto.PaymentDto;

public interface PaymentService {
  Long receivePayment(PaymentDto dto);

}
