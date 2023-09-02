package com.order_system.demo.service;

import com.order_system.demo.entity.Order;
import com.order_system.demo.entity.OrderStatus;
import com.order_system.demo.entity.Ticket;
import com.order_system.demo.repository.OrderRepository;
import dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
  private final OrderRepository orderRepository;
  private final TicketService ticketService;

  @Autowired
  public OrderServiceImpl(OrderRepository orderRepository, TicketService ticketService) {
    this.orderRepository = orderRepository;
    this.ticketService = ticketService;
  }

  @Transactional
  @Override
  public Order createOrder(OrderDto orderDto) {
    Order order = orderRepository.save(convertToOrder(orderDto));
    List<Ticket> ticketList = ticketService.convertToTickets(orderDto.getTickets(), order);
    ticketService.saveTickets(ticketList);
    return order;
  }

  @Override
  public Order findOrderById(Long orderId) {
    return orderRepository.findById(orderId).get();
  }

  private Order convertToOrder(OrderDto orderDto) {
    Order order = new Order();
    order.setId(orderDto.getId());
    order.setUserName(orderDto.getUserName());
    order.setTime(LocalDateTime.now());
    order.setStatus(OrderStatus.NEW);
    return order;
  }
}
