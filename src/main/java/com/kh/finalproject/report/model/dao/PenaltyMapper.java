package com.kh.finalproject.report.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.report.model.dto.PenaltyDTO;
import com.kh.finalproject.report.model.vo.PenaltyVO;

@Mapper
public interface PenaltyMapper {

  void addByPenalty(PenaltyVO penaltyVO);

  void updateByPenalty(PenaltyVO penaltyVO);

  void deleteByPenalty(Long penaltyNo);

  List<PenaltyDTO> findByPenalty();
}
