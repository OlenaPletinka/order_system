package com.order_system.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order_system.demo.entity.Order;
import dto.OrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageProcessorImpl implements MessageProcessor {
  private final OrderService orderService;
  private final TicketService ticketService;
  private final ObjectMapper mapper = new ObjectMapper();
  private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessorImpl.class);

  @Autowired
  public MessageProcessorImpl(OrderService orderService, TicketService ticketService) {
    this.orderService = orderService;
    this.ticketService = ticketService;
  }

  @Override
  public void processOrderReceivingMessage(OrderDto dto) {
    Order order = orderService.createOrder(dto);
    LOGGER.info(String.format("Order with id %d was created and saved.", order.getId()));

  }
}
