package com.order_system.demo.service;

import com.order_system.demo.entity.Order;
import dto.OrderDto;


public interface OrderService {
  Order createOrder(OrderDto orderDto);
}
