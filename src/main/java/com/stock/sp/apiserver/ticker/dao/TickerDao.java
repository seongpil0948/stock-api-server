package com.stock.sp.apiserver.ticker.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.stock.sp.apiserver.ticker.dto.req.TickerReadListReqDto;
import com.stock.sp.apiserver.ticker.dto.req.TickerReadReqDto;
import com.stock.sp.apiserver.ticker.entity.Ticker;

@Mapper
@Repository
public interface TickerDao {
  public List<Ticker> selectTickerList(TickerReadListReqDto dto);

  public Ticker selectTicker(TickerReadReqDto dto);

}
