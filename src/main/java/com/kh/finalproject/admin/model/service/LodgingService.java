package com.kh.finalproject.admin.model.service;

import java.util.List;

import com.kh.finalproject.admin.model.dto.LodgingDTO;

public interface LodgingService {
  
  void addByLodging(LodgingDTO lodgingDTO);

  void updateByLodging(LodgingDTO lodgingDTO);

  void deleteByLodging(Long id);

  List<LodgingDTO> findByLodging();

  LodgingDTO findByLodgingId(Long id);
}
