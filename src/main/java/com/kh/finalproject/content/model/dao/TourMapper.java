package com.kh.finalproject.content.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.content.model.vo.Tour;

@Mapper
public interface TourMapper {
	
	void insertTour(Tour tour);
	void updateTour(Tour tour);

}
