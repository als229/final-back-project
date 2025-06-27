package com.kh.finalproject.global.map.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.global.map.model.vo.AddrVO;
import com.kh.finalproject.global.map.model.vo.MapVO;


@Mapper
public interface MapMapper {
  
  void addByMap(MapVO mapVO);
  void addBySido(AddrVO addrVO);
  void addBySigungu(AddrVO addrVO);
  void addByDong(AddrVO addrVO);
  void addByDetailAdd(AddrVO addrVO);
}
