package com.kh.finalproject.diner.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.diner.model.dto.DinerDTO;
import com.kh.finalproject.diner.model.vo.DinerVO;

@Mapper
public interface DinerMapper {
  
  void addByDiner(DinerVO dinerVO);

  void updateByDiner(DinerVO dinerVO);

  DinerDTO findByDinerId(Long id);
}
