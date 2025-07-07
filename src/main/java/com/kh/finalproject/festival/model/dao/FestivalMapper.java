package com.kh.finalproject.festival.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.festival.model.dto.FestivalDTO;
import com.kh.finalproject.festival.model.vo.FestivalVO;

@Mapper
public interface FestivalMapper {

  void addByFestival(FestivalVO festivalVO);

  void updateByFestival(FestivalVO festivalVO);

  FestivalDTO findByFestivalId(Long id);
}
