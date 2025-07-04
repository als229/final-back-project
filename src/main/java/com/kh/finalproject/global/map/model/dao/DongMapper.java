package com.kh.finalproject.global.map.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.global.map.model.dto.DongDTO;
import com.kh.finalproject.global.map.model.vo.DongVO;

@Mapper
public interface DongMapper {

  void addByDong(DongVO dongVO);

  void updateByDong(DongVO dongVO);

  void deleteByDong(Long dongNo);

  List<DongDTO> findByDong(Long sigunguNo);
}
