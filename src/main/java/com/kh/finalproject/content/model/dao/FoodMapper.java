package com.kh.finalproject.content.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.content.model.dto.FoodDTO;
import com.kh.finalproject.content.model.vo.Food;

@Mapper
public interface FoodMapper {
	
	void insertFood(Food food);
	void updateFood(Food food);
	FoodDTO findByContentId(Long contentId);


}
