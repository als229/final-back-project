package com.kh.finalproject.global.map.model.service;

import com.kh.finalproject.global.map.model.dto.DetailViewDTO;

public interface DetailViewService {
  
  void addByDetailView(DetailViewDTO detailViewDTO);

  void updateByDetailView(DetailViewDTO detailViewDTO);

  void deleteByDetailView(Long detailViewNo);

  DetailViewDTO findByDetailView(Long contentId);
}
