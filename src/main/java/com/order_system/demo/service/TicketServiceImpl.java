package com.order_system.demo.service;

import com.order_system.demo.dto.TicketDto;
import com.order_system.demo.entity.Order;
import com.order_system.demo.entity.Ticket;
import com.order_system.demo.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
  private final TicketRepository ticketRepository;

  @Autowired
  public TicketServiceImpl(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }

  @Override
  public void saveTickets(List<Ticket> tickets) {
    ticketRepository.saveAll(tickets);
  }

  @Override
  public List<Ticket> convertToTickets(List<TicketDto> tickets, Order order) {
    List<Ticket> ticketList = new ArrayList<>();
    for (TicketDto dto : tickets) {
      Ticket ticket = new Ticket();
      ticket.setOrders(order);
      ticket.setSeatsNumber(dto.getSeatsNumber());
      ticket.setEventName(dto.getEventName());
      ticket.setEventTime(dto.getEventTime());
      ticketList.add(ticket);
    }
    return ticketList;
  }
}
