package com.order_system.demo.controller;

import com.order_system.demo.dto.Order;
import com.order_system.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/orders")
public class OrderController {
  private final OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping(path = "/createOrder")
  @ResponseStatus(HttpStatus.OK)
  public String createOrder(@RequestParam String description){

    Order order = orderService.createOrder(description);
    return "Order with id "+ order.getId() + " successfully created.";

  }
}
