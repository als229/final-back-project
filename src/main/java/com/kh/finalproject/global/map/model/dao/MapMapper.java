package com.kh.finalproject.global.map.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.global.map.model.dto.MapDTO;
import com.kh.finalproject.global.map.model.vo.MapVO;


@Mapper
public interface MapMapper {
  
  void addByMap(MapVO mapVO);

  void updateByMap(MapVO mapVO);

  void deleteByMap(Long contentId);

  MapDTO findByMapId(Long contentId);
}
