package com.order_system.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ticket {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "order_id", referencedColumnName = "id")
  private Order orders;

  @Column
  private Integer seatsNumber;

  @Column
  private String eventName;

  @Column
  private LocalDateTime eventTime;
}
