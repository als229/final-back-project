package com.kh.finalproject.content.model.service;

import org.springframework.stereotype.Service;

import com.kh.finalproject.content.model.dao.TourMapper;
import com.kh.finalproject.content.model.dto.TourDTO;
import com.kh.finalproject.content.model.vo.Tour;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {
	
	private final TourMapper tourMapper;

	@Override
	public void insertTour(TourDTO tour, Long contentId) {
		
		Tour requestData = Tour.builder()
				.contentId(contentId)
				.tourExp(tour.getTourExp())
				.usetimeTour(tour.getUsetimeTour())
				.parking(tour.getParking())
				.build();
		
		tourMapper.insertTour(requestData);
		
	}

}
