package com.kh.finalproject.content.model.service;

import com.kh.finalproject.content.model.dto.FoodDTO;

public interface FoodService {
	
	void insertFood(FoodDTO food, Long contentId);

}
