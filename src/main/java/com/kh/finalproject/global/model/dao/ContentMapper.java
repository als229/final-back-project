package com.kh.finalproject.global.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.global.model.dto.ContentDTO;
import com.kh.finalproject.global.model.vo.ContentVO;

@Mapper
public interface ContentMapper {

  void addByContent(ContentVO contentVO);

  void updateByContent(ContentVO contentVO);

  void deleteByContent(Long id);

  List<ContentDTO> findByContent(Long categoryCode);
}
