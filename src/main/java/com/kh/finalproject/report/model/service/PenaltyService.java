package com.kh.finalproject.report.model.service;

import java.util.List;

import com.kh.finalproject.report.model.dto.PenaltyDTO;

public interface PenaltyService {

  void addByPenalty(PenaltyDTO penaltyDTO);

  void updateByPenalty(PenaltyDTO penaltyDTO);

  void deleteByPenalty(Long penaltyNo);

  List<PenaltyDTO> findByPenalty();
}
