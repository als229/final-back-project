package com.kh.finalproject.content.model.service;

import com.kh.finalproject.content.model.dto.CoordinateDTO;

public interface CoordinateService {
	
	void insertCoordinate(CoordinateDTO coordinate, Long contentId); 
	void updateCoordinate(CoordinateDTO coordinate, Long contentId); 

}
