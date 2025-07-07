package com.kh.finalproject.content.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.content.model.dto.CoordinateDTO;
import com.kh.finalproject.content.model.vo.Coordinate;

@Mapper
public interface CoordinateMapper {
	
	void insertCoordinate(Coordinate coordinate);
	void updateCoordinate(Coordinate coordinate);
	CoordinateDTO findByContentId(Long contentId);


}
