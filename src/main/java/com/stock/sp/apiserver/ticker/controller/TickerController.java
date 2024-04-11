package com.stock.sp.apiserver.ticker.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.stock.sp.apiserver.ticker.entity.Ticker;
import com.stock.sp.apiserver.ticker.service.TickerService;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@RestController

public class TickerController {
  @Autowired
  TickerService tickerService;

  @GetMapping("/")
  public Map<String, Object> greeting() {
    return Collections.singletonMap("message", "Hello, World");
  }

  @GetMapping("/ticker")
  public List<Ticker> getTickerList() {
    return tickerService.getTickerList();
  }
}
