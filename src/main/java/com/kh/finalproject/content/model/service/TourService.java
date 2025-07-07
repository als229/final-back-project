package com.kh.finalproject.content.model.service;

import com.kh.finalproject.content.model.dto.TourDTO;

public interface TourService {
	
	void insertTour(TourDTO tour, Long contentId);
	void updateTour(TourDTO tour, Long contentId);

}
