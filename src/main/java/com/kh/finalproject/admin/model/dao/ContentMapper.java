package com.kh.finalproject.admin.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.admin.model.dto.FoodDTO;
import com.kh.finalproject.admin.model.dto.LodgingDTO;
import com.kh.finalproject.admin.model.vo.ContentVO;
import com.kh.finalproject.admin.model.vo.FoodVO;
import com.kh.finalproject.admin.model.vo.LodgingVO;
import com.kh.finalproject.admin.model.vo.mapVO;

@Mapper
public interface ContentMapper {

  void addByContent(ContentVO contentVO);
  void addByFood(FoodVO foodVO);
  void addByLodging(LodgingVO lodgingVO);
  void addByContentMap(mapVO mapVO);

  void updateByContent(ContentVO contentVO);
  void updateByFood(FoodVO foodVO);
  void updateByLodging(LodgingDTO lodgingDTO);

  void deleteByContent(Long id);

  List<FoodDTO> findByFood();
  FoodDTO findByFoodId(Long id);

  List<LodgingDTO> findByLodging();
  LodgingDTO findByLodgingId(Long id);
}
