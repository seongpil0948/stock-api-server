package com.stock.sp.apiserver.ticker.dto.req;

import lombok.Data;

@Data
public class TickerReadReqDto {
  String ticker;
  String date;
}
