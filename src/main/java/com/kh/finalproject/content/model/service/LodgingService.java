package com.kh.finalproject.content.model.service;

import com.kh.finalproject.content.model.dto.LodgingDTO;

public interface LodgingService {

	void insertLodging(LodgingDTO lodging, Long contentId);
	void updateLodging(LodgingDTO lodging, Long contentId);
	
}
