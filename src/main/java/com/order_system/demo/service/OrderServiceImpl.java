package com.order_system.demo.service;

import com.order_system.demo.dto.Order;
import com.order_system.demo.dto.Status;
import com.order_system.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService{
  private final OrderRepository orderRepository;

  @Autowired
  public OrderServiceImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public Order createOrder(String description) {
    Order order = new Order();
    order.setDate(LocalDateTime.now());
    order.setStatus(Status.NEW);
    order.setDescription(description);
    return orderRepository.save(order);
  }
}
