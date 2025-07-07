package com.kh.finalproject.tour.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.tour.model.dto.TourDTO;

public interface TourService {

  void addByTour(TourDTO tourDTO, List<MultipartFile> files);

  void updateByTour(TourDTO tourDTO, List<MultipartFile> files);

  void deleteByTour(Long id);

  List<?> findByTour();
  
  TourDTO findByTourId(Long id);
}
