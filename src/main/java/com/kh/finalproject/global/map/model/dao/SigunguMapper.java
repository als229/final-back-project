package com.kh.finalproject.global.map.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.global.map.model.dto.SigunguDTO;
import com.kh.finalproject.global.map.model.vo.SigunguVO;

@Mapper
public interface SigunguMapper {

  void addBySigungu(SigunguVO sidoVO);

  void updateBySigungu(SigunguVO sidoVO);

  void deleteBySigungu(Long sigunguNo);

  List<SigunguDTO> findBySigungu(Long sidoNo);
}
