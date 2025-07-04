package com.kh.finalproject.report.model.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.finalproject.exception.exceptions.NullPointException;
import com.kh.finalproject.report.model.dao.PenaltyMapper;
import com.kh.finalproject.report.model.dto.PenaltyDTO;
import com.kh.finalproject.report.model.vo.PenaltyVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PenaltyServiceImpl implements PenaltyService {

  private final PenaltyMapper penaltyMapper;

  @Override
  @Transactional
  // @PreAuthorize("hasRole('ADMIN')")
  public void addByPenalty(PenaltyDTO penaltyDTO) {

    penaltyMapper.addByPenalty(
      PenaltyVO.builder()
               .penaltyName(penaltyDTO.getPenaltyName())
               .build()
    );
  }

  @Override
  @Transactional
  // @PreAuthorize("hasRole('ADMIN')")
  public void updateByPenalty(PenaltyDTO penaltyDTO) {

    penaltyMapper.updateByPenalty(
      PenaltyVO.builder()
               .penaltyNo(penaltyDTO.getPenaltyNo())
               .penaltyName(penaltyDTO.getPenaltyName())
               .build()
    );
  }

  @Override
  @Transactional
  // @PreAuthorize("hasRole('ADMIN')")
  public void deleteByPenalty(Long penaltyNo) {

    if(penaltyNo == null) { throw new NullPointException("패널티 번호는 필수입니다."); }
    penaltyMapper.deleteByPenalty(penaltyNo);
  }

  @Override
  public List<PenaltyDTO> findByPenalty() {

    List<PenaltyDTO> list = penaltyMapper.findByPenalty(); 
    return list;
  }
}
