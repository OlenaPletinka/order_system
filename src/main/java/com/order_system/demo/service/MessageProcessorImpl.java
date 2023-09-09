package com.order_system.demo.service;

import com.order_system.demo.dto.ReservationDto;
import com.order_system.demo.dto.TicketDto;
import com.order_system.demo.entity.Order;
import com.order_system.demo.dto.OrderDto;
import com.order_system.demo.dto.PaymentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageProcessorImpl implements MessageProcessor {
  private final OrderService orderService;
  private final PaymentService paymentService;
  private final ReservationKafkaProducer reservationKafkaProducer;
  private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessorImpl.class);

  @Autowired
  public MessageProcessorImpl(OrderService orderService, PaymentService paymentService, ReservationKafkaProducer reservationKafkaProducer) {
    this.orderService = orderService;
    this.paymentService = paymentService;
    this.reservationKafkaProducer = reservationKafkaProducer;
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

  @Override
  public void processRegistration(OrderDto dto) {
    LOGGER.info("Messege was sent to Registration microservice");
    List<ReservationDto>dtos = createReservationsList(dto);
    reservationKafkaProducer.sendMessageToReservation(dtos);
  }

  private List<ReservationDto> createReservationsList(OrderDto dto) {
    String userName = dto.getUserName();
    List<TicketDto> tickets = dto.getTickets();
    List<ReservationDto>dtos = new ArrayList<>();
    for(TicketDto t : tickets){
      ReservationDto reservationDto =
                new ReservationDto(userName,t.getSeatsNumber(), t.getEventName(), t.getEventTime());
      dtos.add(reservationDto);
    }
    return dtos;
  }
}
