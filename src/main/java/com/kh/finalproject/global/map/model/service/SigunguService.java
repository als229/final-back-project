package com.kh.finalproject.global.map.model.service;

import java.util.List;

import com.kh.finalproject.global.map.model.dto.SigunguDTO;

public interface SigunguService {
  
  void addBySigungu(SigunguDTO sigunguDTO);

  void updateBySigungu(SigunguDTO sigunguDTO);

  void deleteBySigungu(Long sigunguNo);

  List<SigunguDTO> findBySigungu(Long sidoNo);
}
