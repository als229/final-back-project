package com.kh.finalproject.festival.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.festival.model.dto.FestivalDTO;

public interface FestivalService {

  void addByFestival(FestivalDTO festivalDTO, List<MultipartFile> files);

  void updateByFestival(FestivalDTO festivalDTO, List<MultipartFile> files);

  void deleteByFestival(Long id);

  List<?> findByFestival();

  FestivalDTO findByFestivalId(Long id);
}
