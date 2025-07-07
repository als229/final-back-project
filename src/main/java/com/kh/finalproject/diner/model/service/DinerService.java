package com.kh.finalproject.diner.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.diner.model.dto.DinerDTO;

public interface DinerService {

  void addByDiner(DinerDTO foodDTO, List<MultipartFile> files);

  void updateByDiner(DinerDTO foodDTO, List<MultipartFile> files);

  void deleteByDiner(Long Id);

  List<?> findByDiner();

  DinerDTO findByDinerId(Long id);
}
