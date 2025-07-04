package com.kh.finalproject.global.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.global.model.dto.CategoryDTO;
import com.kh.finalproject.global.model.vo.CategoryVO;

@Mapper
public interface CategoryMapper {

  void addByCategory(CategoryVO categoryVO);

  void updateByCategory(CategoryVO categoryVO);

  void deleteByCategory(Long categoryCode);

  List<CategoryDTO> findByCategory();
}
