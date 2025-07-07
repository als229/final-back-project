package com.kh.finalproject.global.map.model.service;

import com.kh.finalproject.global.map.model.dto.DetailAddDTO;

public interface DetailAddService {
  
  void addByDetailAdd(DetailAddDTO detailAddDTO);

  void updateByDetailAdd(DetailAddDTO detailAddDTO);

  void deleteByDetailAdd(Long detailAddNo);

  DetailAddDTO findByDetailAdd(Long contentId);
}
