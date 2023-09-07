package com.order_system.demo.service;

import com.order_system.demo.entity.Order;
import com.order_system.demo.entity.Ticket;
import com.order_system.demo.dto.TicketDto;

import java.util.List;

public interface TicketService {
  void saveTickets(List<Ticket>tickets);
  List<Ticket> convertToTickets(List<TicketDto>tickets, Order order);
}
