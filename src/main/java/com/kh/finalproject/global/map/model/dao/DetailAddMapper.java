package com.kh.finalproject.global.map.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.global.map.model.dto.DetailAddDTO;
import com.kh.finalproject.global.map.model.vo.DetailAddVO;

@Mapper
public interface DetailAddMapper {
  
  void addByDetailAdd(DetailAddVO detailAddVO);

  void updateByDetailAdd(DetailAddVO detailAddVO);

  void deleteByDetailAdd(Long contentId);

  DetailAddDTO findByDetailAdd(Long contentId);
}
