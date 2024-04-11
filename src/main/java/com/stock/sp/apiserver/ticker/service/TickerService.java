package com.stock.sp.apiserver.ticker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.sp.apiserver.ticker.dao.TickerDao;
import com.stock.sp.apiserver.ticker.entity.Ticker;

@Service
public class TickerService {
  @Autowired
  TickerDao tickerDao;

  public List<Ticker> getTickerList() {
    List<Ticker> result = tickerDao.selectTickerList();
    // log result count and first item
    System.out.println("result count: " + result.size());
    if (result.size() > 0) {
      Ticker firstItem = result.get(0);
      System.out.println("first item: " + firstItem);
    }
    return result;
  }

}
