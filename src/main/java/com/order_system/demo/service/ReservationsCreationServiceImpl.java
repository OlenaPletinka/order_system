package com.order_system.demo.service;

import com.order_system.demo.dto.ReservationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ReservationsCreationServiceImpl implements ReservationCreationService{
  private static final Logger LOGGER = LoggerFactory.getLogger(ReservationsCreationServiceImpl.class);
  private final RestTemplate restTemplate;

  @Autowired
  public ReservationsCreationServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public void createReservations(List<ReservationDto> dtos) {
    String url = "http://localhost:8090/reservation/create";
    ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, dtos, String.class);
    LOGGER.info(String.format("Reseived response - %s", responseEntity.getBody()));
  }
}
