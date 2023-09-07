package com.order_system.demo.service;

import com.order_system.demo.entity.Order;
import com.order_system.demo.dto.OrderDto;
import com.order_system.demo.dto.PaymentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageProcessorImpl implements MessageProcessor {
  private final OrderService orderService;
  private final TicketService ticketService;
  private final PaymentService paymentService;
  private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessorImpl.class);

  @Autowired
  public MessageProcessorImpl(OrderService orderService, TicketService ticketService, PaymentService paymentService) {
    this.orderService = orderService;
    this.ticketService = ticketService;
    this.paymentService = paymentService;
  }

  @Override
  public void processOrderReceivingMessage(OrderDto dto) {
    Order order = orderService.createOrder(dto);
    LOGGER.info(String.format("Order with id %d was created and saved.", order.getId()));

  }

  @Override
  public void processPaymentConfirmationMessage(PaymentDto paymentDto) {
    Long id = paymentService.receivePayment(paymentDto);
    LOGGER.info(String.format("Payment with id %d was created and saved.", id));
  }
}
