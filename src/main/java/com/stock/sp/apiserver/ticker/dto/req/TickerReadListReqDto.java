package com.stock.sp.apiserver.ticker.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@AllArgsConstructor
@Schema(name = "TickerReadListReqDto", description = "Ticker 목록 조회 파라미터")
public class TickerReadListReqDto {

  @Nullable
  @NotBlank(message = "ticker must be null or not blank")
  String ticker;

  @NotNull(message = "startDate is null")
  @NotBlank(message = "startDate is blank")
  String startDate;

  @NotNull(message = "endDate is null")
  @NotBlank(message = "endDate is blank")
  String endDate;
}
