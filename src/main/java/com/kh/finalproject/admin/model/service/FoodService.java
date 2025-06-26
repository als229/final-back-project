package com.kh.finalproject.admin.model.service;

import java.util.List;

import com.kh.finalproject.admin.model.dto.FoodDTO;

public interface FoodService {

  void addByFood(FoodDTO foodDTO);

  void updateByFood(FoodDTO foodDTO);

  void deleteByFood(Long Id);

  List<FoodDTO> findByFood();

  FoodDTO findByFoodId(Long id);
}
