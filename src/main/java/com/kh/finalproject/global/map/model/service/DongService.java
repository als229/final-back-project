package com.kh.finalproject.global.map.model.service;

import java.util.List;

import com.kh.finalproject.global.map.model.dto.DongDTO;

public interface DongService {
  
  void addByDong(DongDTO dongDTO);

  void updateByDong(DongDTO dongDTO);

  void deleteByDong(Long dongNo);

  List<DongDTO> findByDong(Long dongNo);
}
