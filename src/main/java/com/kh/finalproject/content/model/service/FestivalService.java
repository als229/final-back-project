package com.kh.finalproject.content.model.service;

import com.kh.finalproject.content.model.dto.FestivalDTO;

public interface FestivalService {
	
	void insertFestival(FestivalDTO festival, Long contentId);

}
