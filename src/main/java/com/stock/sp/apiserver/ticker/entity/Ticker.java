package com.stock.sp.apiserver.ticker.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Ticker {
  private String date;
  private String ticker;
  private String labelKo;
  private String labelEn;
  private BigDecimal open;
  private BigDecimal high;
  private BigDecimal low;
  private BigDecimal close;
  private BigDecimal volume;
}
