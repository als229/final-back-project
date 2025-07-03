package com.kh.finalproject.global.model.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.finalproject.global.model.dao.CategoryMapper;
import com.kh.finalproject.global.model.dto.CategoryDTO;
import com.kh.finalproject.global.model.vo.CategoryVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryMapper categoryMapper;

  @Override
  @Transactional
  // @PreAuthorize("hasRole('ADMIN')")
  public void addByCategory(CategoryDTO categoryDTO) {

    categoryMapper.addByCategory(
      CategoryVO.builder()
                .categoryCode(categoryDTO.getCategoryCode())
                .categoryName(categoryDTO.getCategoryName())
                .build()
    );
  }

  @Override
  @Transactional
  // @PreAuthorize("hasRole('ADMIN')")
  public void updateByCategory(CategoryDTO categoryDTO) {

    categoryMapper.updateByCategory(
      CategoryVO.builder()
                .categoryCode(categoryDTO.getCategoryCode())
                .categoryName(categoryDTO.getCategoryName())
                .build()
    );
  }

  @Override
  @Transactional
  // @PreAuthorize("hasRole('ADMIN')")
  public void deleteByCategory(Long categoryCode) {

    categoryMapper.deleteByCategory(categoryCode);
  }

  @Override
  public List<CategoryDTO> findByCategory() {
    
    List<CategoryDTO> list = categoryMapper.findByCategory();
    return list; 
  }
}
