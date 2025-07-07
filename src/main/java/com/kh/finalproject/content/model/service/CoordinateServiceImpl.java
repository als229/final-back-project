package com.kh.finalproject.content.model.service;

import org.springframework.stereotype.Service;

import com.kh.finalproject.content.model.dao.CoordinateMapper;
import com.kh.finalproject.content.model.dto.CoordinateDTO;
import com.kh.finalproject.content.model.vo.Coordinate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoordinateServiceImpl implements CoordinateService{
	
	private final CoordinateMapper coordinateMapper;

	@Override
	public void insertCoordinate(CoordinateDTO coordinate, Long contentId) {
		
		Coordinate requestData = Coordinate.builder()
				.contentId(contentId)
				.mapX(coordinate.getMapX())
				.mapY(coordinate.getMapY())
				.build();
		
		coordinateMapper.insertCoordinate(requestData);
		
	}
	
	@Override
	public void updateCoordinate(CoordinateDTO coordinate, Long contentId) {
		
		Coordinate requestData = Coordinate.builder()
				.contentId(contentId)
				.mapX(coordinate.getMapX())
				.mapY(coordinate.getMapY())
				.build();
		
		coordinateMapper.updateCoordinate(requestData);
		
	}

}
