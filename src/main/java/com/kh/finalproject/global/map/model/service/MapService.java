package com.kh.finalproject.global.map.model.service;

import com.kh.finalproject.global.map.model.dto.MapDTO;

public interface MapService {
  
  void addByMap(MapDTO mapDTO);

  void updateByMap(MapDTO mapDTO);

  MapDTO findByMapId(Long contentId);
  
  MapDTO getMap(Long contentId);
}
