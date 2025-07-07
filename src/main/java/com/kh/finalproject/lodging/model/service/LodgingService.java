package com.kh.finalproject.lodging.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.lodging.model.dto.LodgingDTO;

public interface LodgingService {
  
  void addByLodging(LodgingDTO lodgingDTO, List<MultipartFile> files);

  void updateByLodging(LodgingDTO lodgingDTO, List<MultipartFile> files);

  void deleteByLodging(Long id);

  List<?> findByLodging();

  LodgingDTO findByLodgingId(Long id);
}
