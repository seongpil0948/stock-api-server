package com.stock.sp.apiserver.ticker.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import com.stock.sp.apiserver.ticker.dto.req.TickerReadListReqDto;
import com.stock.sp.apiserver.ticker.dto.req.TickerReadReqDto;
import com.stock.sp.apiserver.ticker.entity.Ticker;
import com.stock.sp.apiserver.ticker.service.TickerService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/ticker")
@Tag(name = "ticker", description = "Ticker 관리")
public class TickerController {
  @Autowired
  TickerService tickerService;

  @Operation(summary = "Get ticker list")
  @GetMapping("/")
  @Parameters({
      @Parameter(name = "ticker", description = "특정 Ticker", example = "TSLA", schema = @Schema(type = "string")),
      @Parameter(name = "startDate", description = "시작 Date", example = "2023-06-01", schema = @Schema(type = "string")),
      @Parameter(name = "endDate", description = "종료 Date", example = "2023-12-31", schema = @Schema(type = "string")),
  })
  public List<Ticker> getTickerList(@Parameter(hidden = true) TickerReadListReqDto dto) {
    return tickerService.getTickerList(dto);
  }

  @Operation(summary = "Get single ticker by date")
  @GetMapping("/{ticker}")
  // @Parameters({
  // @Parameter(name = "date", description = "특정 Date", example = "ALL",
  // schema=@Schema(type="string")),
  // })
  public Ticker getTicker(@PathVariable String ticker, @RequestParam String date) {
    log.info("ticker: {}, date: {}", ticker, date);
    TickerReadReqDto dto = new TickerReadReqDto();
    dto.setTicker(ticker);
    dto.setDate(date);
    return tickerService.getTicker(dto);
  }

}
