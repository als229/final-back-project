package com.kh.finalproject.global.model.service;

import java.util.List;

import com.kh.finalproject.global.model.dto.CategoryDTO;

public interface CategoryService {

  void addByCategory(CategoryDTO categoryDTO);

  void updateByCategory(CategoryDTO categoryDTO);

  void deleteByCategory(Long categoryCode);

  List<CategoryDTO> findByCategory();
}
