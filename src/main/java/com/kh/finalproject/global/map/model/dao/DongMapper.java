package com.kh.finalproject.global.map.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.global.map.model.dto.DongDTO;
import com.kh.finalproject.global.map.model.vo.DongVO;

@Mapper
public interface DongMapper {

  void addByDong(DongVO dongVO);

  void updateByDong(DongVO dongVO);

  void deleteByDong(Long dongNo);

  DongDTO findByDong(Long dongNo);
}
