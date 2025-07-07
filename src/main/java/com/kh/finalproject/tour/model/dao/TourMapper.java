package com.kh.finalproject.tour.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.tour.model.dto.TourDTO;
import com.kh.finalproject.tour.model.vo.TourVO;

@Mapper
public interface TourMapper {

  void addByTour(TourVO tourVO);

  void updateByTour(TourVO tourVO);
  
  TourDTO findByTourId(Long id);
}
