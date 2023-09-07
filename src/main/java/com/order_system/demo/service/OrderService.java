package com.order_system.demo.service;

import com.order_system.demo.entity.Order;
import com.order_system.demo.dto.OrderDto;


public interface OrderService {
  Order createOrder(OrderDto orderDto);

  Order findOrderById(Long orderId);
}
