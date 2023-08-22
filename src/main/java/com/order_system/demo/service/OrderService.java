package com.order_system.demo.service;

import com.order_system.demo.dto.Order;


public interface OrderService {
  Order createOrder(String description);
}
