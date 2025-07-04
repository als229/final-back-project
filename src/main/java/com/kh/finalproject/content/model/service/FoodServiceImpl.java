package com.kh.finalproject.content.model.service;

import org.springframework.stereotype.Service;

import com.kh.finalproject.content.model.dao.FoodMapper;
import com.kh.finalproject.content.model.dto.FoodDTO;
import com.kh.finalproject.content.model.vo.Food;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService{
	
	private final FoodMapper foodMapper;

	@Override
	public void insertFood(FoodDTO food, Long contentId) {
		
		Food requestData = Food.builder()
				.contentId(contentId)
				.foodExp(food.getFoodExp())
				.mainMenu(food.getMainMenu())
				.menu(food.getMenu())
				.parking(food.getParking())
				.build();
		
		foodMapper.insertFood(requestData);
		
	}
	
	@Override
	public void updateFood(FoodDTO food, Long contentId) {
		
		Food requestData = Food.builder()
				.contentId(contentId)
				.foodExp(food.getFoodExp())
				.mainMenu(food.getMainMenu())
				.menu(food.getMenu())
				.parking(food.getParking())
				.build();
		
		foodMapper.updateFood(requestData);
		
	}

}
