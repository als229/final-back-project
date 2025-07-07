package com.kh.finalproject.global.map.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.global.map.model.dto.SidoDTO;
import com.kh.finalproject.global.map.model.vo.SidoVO;

@Mapper
public interface SidoMapper {

  void addBySido(SidoVO sidoVO);

  void updateBySido(SidoVO sidoVO);

  void deleteBySido(Long sidoNo);

  List<SidoDTO> findBySido();
}
