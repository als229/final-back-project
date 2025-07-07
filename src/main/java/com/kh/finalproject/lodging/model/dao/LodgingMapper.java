package com.kh.finalproject.lodging.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.lodging.model.dto.LodgingDTO;
import com.kh.finalproject.lodging.model.vo.LodgingVO;

@Mapper
public interface LodgingMapper {
  
  void addByLodging(LodgingVO lodgingVO);

  void updateByLodging(LodgingVO lodgingVO);

  LodgingDTO findByLodgingId(Long id);
}
