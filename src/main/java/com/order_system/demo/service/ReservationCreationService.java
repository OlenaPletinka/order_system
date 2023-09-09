package com.order_system.demo.service;

import com.order_system.demo.dto.ReservationDto;

import java.util.List;

public interface ReservationCreationService {
  void createReservations(List<ReservationDto> dtos);
}
