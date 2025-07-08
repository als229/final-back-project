package com.kh.finalproject.global.map.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.global.map.model.dto.DetailViewDTO;
import com.kh.finalproject.global.map.model.vo.DetailViewVO;

@Mapper
public interface DetailViewMapper {
  
  void addByDetailView(DetailViewVO detailViewVO);

  void updateByDetailView(DetailViewVO detailViewVO);

  void deleteByDetailView(Long contentId);

  DetailViewDTO findByDetailView(Long contentId);
}
