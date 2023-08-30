package com.order_system.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @OneToOne
  @JoinColumn(name = "order_id", referencedColumnName = "id")
  private Order orders;

  @Column
  private LocalDateTime date;

  @Column
  private BigDecimal total;

  @Enumerated(EnumType.STRING)
  private PaymentStatus status;
}
