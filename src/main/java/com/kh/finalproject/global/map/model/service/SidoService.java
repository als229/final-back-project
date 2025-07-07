package com.kh.finalproject.global.map.model.service;

import java.util.List;

import com.kh.finalproject.global.map.model.dto.SidoDTO;

public interface SidoService {
  
  void addBySido(SidoDTO sidoDTO);

  void updateBySido(SidoDTO sidoDTO);
  
  void deleteBySido(Long sidoNo);

  List<SidoDTO> findBySido();
}
