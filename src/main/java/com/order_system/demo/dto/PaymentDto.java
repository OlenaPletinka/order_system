package com.order_system.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PaymentDto {
  private Long orderId;

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime date;
  private BigDecimal total;
}
