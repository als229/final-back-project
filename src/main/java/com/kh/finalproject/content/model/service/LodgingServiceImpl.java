package com.kh.finalproject.content.model.service;

import org.springframework.stereotype.Service;

import com.kh.finalproject.content.model.dao.LodgingMapper;
import com.kh.finalproject.content.model.dto.LodgingDTO;
import com.kh.finalproject.content.model.vo.Lodging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LodgingServiceImpl implements LodgingService{
	
	private final LodgingMapper lodgingMapper;

	@Override
	public void insertLodging(LodgingDTO lodging, Long contentId) {
		
		Lodging requestData = Lodging.builder()
				.contentId(contentId)
				.lodgingExp(lodging.getLodgingExp())
				.checkIn(lodging.getCheckIn())
				.checkOut(lodging.getCheckOut())
				.parking(lodging.getParking())
				.elevator(lodging.getElevator())
				.build();
		
		lodgingMapper.insertLodging(requestData);
		
	}

}
