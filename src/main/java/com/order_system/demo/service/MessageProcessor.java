package com.order_system.demo.service;

import com.order_system.demo.dto.OrderDto;
import com.order_system.demo.dto.PaymentDto;

public interface MessageProcessor {
  void processOrderReceivingMessage(OrderDto dto);

  void processPaymentConfirmationMessage(PaymentDto paymentDto);
}
