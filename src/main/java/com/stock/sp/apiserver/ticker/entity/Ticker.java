package com.stock.sp.apiserver.ticker.entity;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "TICKER 정보")
public class Ticker {
  @Schema(description = "날짜")
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
