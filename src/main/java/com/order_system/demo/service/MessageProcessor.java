package com.order_system.demo.service;

import dto.OrderDto;

public interface MessageProcessor {
  void processOrderReceivingMessage(OrderDto dto);
}
