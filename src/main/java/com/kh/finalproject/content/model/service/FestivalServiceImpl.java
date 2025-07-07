package com.kh.finalproject.content.model.service;

import org.springframework.stereotype.Service;

import com.kh.finalproject.content.model.dao.FestivalMapper;
import com.kh.finalproject.content.model.dto.FestivalDTO;
import com.kh.finalproject.content.model.vo.Festival;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FestivalServiceImpl implements FestivalService{
	
	private final FestivalMapper fetivalMapper;

	@Override
	public void insertFestival(FestivalDTO festival, Long contentId) {
		
		Festival requestData = Festival.builder()
				.contentId(contentId)
				.program(festival.getProgram())
				.eventExp(festival.getEventExp())
				.sponsor(festival.getSponsor())
				.usetimeFestival(festival.getUsetimeFestival())
				.eventStartDate(festival.getEventStartDate())
				.eventEndDate(festival.getEventEndDate())
				.build();
		
		fetivalMapper.insertFestival(requestData);
	}
	
	@Override
	public void updateFestival(FestivalDTO festival, Long contentId) {
		
		Festival requestData = Festival.builder()
				.contentId(contentId)
				.program(festival.getProgram())
				.eventExp(festival.getEventExp())
				.sponsor(festival.getSponsor())
				.usetimeFestival(festival.getUsetimeFestival())
				.eventStartDate(festival.getEventStartDate())
				.eventEndDate(festival.getEventEndDate())
				.build();
		
		fetivalMapper.updateFestival(requestData);
	}

}
