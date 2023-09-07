package com.order_system.demo.service;

import com.order_system.demo.entity.Order;
import com.order_system.demo.entity.OrderStatus;
import com.order_system.demo.entity.Payment;
import com.order_system.demo.entity.PaymentStatus;
import com.order_system.demo.repository.PaymentRepository;
import com.order_system.demo.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService {
  private final OrderService orderService;
  private final PaymentRepository paymentRepository;

  @Autowired
  public PaymentServiceImpl(OrderService orderService, PaymentRepository paymentRepository) {
    this.orderService = orderService;
    this.paymentRepository = paymentRepository;
  }

  @Transactional
  @Override
  public Long receivePayment(PaymentDto dto) {
    Order order = orderService.findOrderById(dto.getOrderId());
    Payment payment = paymentRepository.save(convertDtoToPayment(dto, order));
    order.setStatus(OrderStatus.PAYED);
    return payment.getId();
  }

  private Payment convertDtoToPayment(PaymentDto dto, Order order) {
    Payment payment = new Payment();
    payment.setOrders(order);
    payment.setDate(dto.getDate());
    payment.setTotal(dto.getTotal());
    payment.setStatus(PaymentStatus.SUCCESS);
    return payment;
  }
}
