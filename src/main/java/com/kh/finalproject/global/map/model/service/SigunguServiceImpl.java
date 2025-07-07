package com.kh.finalproject.global.map.model.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.finalproject.exception.exceptions.NullPointException;
import com.kh.finalproject.global.map.model.dao.SigunguMapper;
import com.kh.finalproject.global.map.model.dto.SigunguDTO;
import com.kh.finalproject.global.map.model.vo.SigunguVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SigunguServiceImpl implements SigunguService {

  private final SigunguMapper sigunguMapper;
  
  @Override
  @Transactional
  // @PreAuthorize("hasRole('ADMIN')")
  public void addBySigungu(SigunguDTO sigunguDTO) {

    sigunguMapper.addBySigungu(
      SigunguVO.builder()
               .sidoNo(sigunguDTO.getSidoNo())
               .sigunguName(sigunguDTO.getSigunguName())
               .build());
  }

  @Override
  @Transactional
  // @PreAuthorize("hasRole('ADMIN')")
  public void updateBySigungu(SigunguDTO sigunguDTO) {

    sigunguMapper.updateBySigungu(
      SigunguVO.builder()
               .sigunguNo(sigunguDTO.getSigunguNo())
               .sidoNo(sigunguDTO.getSidoNo())
               .sigunguName(sigunguDTO.getSigunguName())
               .build());
  }

  @Override
  @Transactional
  // @PreAuthorize("hasRole('ADMIN')")
  public void deleteBySigungu(Long sigunguNo) {

    if(sigunguNo == null) { throw new NullPointException("시군구 번호는 필수입니다."); }
    sigunguMapper.deleteBySigungu(sigunguNo);
  }

  @Override
  public List<SigunguDTO> findBySigungu(Long sidoNo) {
    
  List<SigunguDTO> list = sigunguMapper.findBySigungu(sidoNo);
    return list; 
  } 
}
