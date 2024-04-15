package com.stock.sp.apiserver.ticker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.sp.apiserver.ticker.dao.TickerDao;
import com.stock.sp.apiserver.ticker.dto.req.TickerReadListReqDto;
import com.stock.sp.apiserver.ticker.dto.req.TickerReadReqDto;
import com.stock.sp.apiserver.ticker.entity.Ticker;

@Service
public class TickerService {
  @Autowired
  TickerDao tickerDao;

  public List<Ticker> getTickerList(TickerReadListReqDto dto) {
    List<Ticker> result = tickerDao.selectTickerList(dto);
    System.out.println("result count: " + result.size());
    return result;
  }

  public Ticker getTicker(TickerReadReqDto dto) {
    return tickerDao.selectTicker(dto);
  }

}
