package com.kh.finalproject.global.map.model.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.finalproject.exception.exceptions.NullPointException;
import com.kh.finalproject.global.map.model.dao.SidoMapper;
import com.kh.finalproject.global.map.model.dto.SidoDTO;
import com.kh.finalproject.global.map.model.vo.SidoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SidoServiceImpl implements SidoService {

  private final SidoMapper sidoMapper;

    @Override
    @Transactional
    // @PreAuthorize("hasRole('ADMIN')")
    public void addBySido(SidoDTO sidoDTO) {
      
      sidoMapper.addBySido(
        SidoVO.builder()
              .sidoName(sidoDTO.getSidoName())
              .build()
      );
    }

    @Override
    @Transactional
    // @PreAuthorize("hasRole('ADMIN')")
    public void updateBySido(SidoDTO sidoDTO) {

      sidoMapper.updateBySido(
        SidoVO.builder()
              .sidoNo(sidoDTO.getSidoNo())
              .sidoName(sidoDTO.getSidoName())
              .build()
      );
    }

    @Override
    @Transactional
    // @PreAuthorize("hasRole('ADMIN')")
    public void deleteBySido(Long sidoNo) {

      if(sidoNo == null) { throw new NullPointException("시도 번호는 필수입니다."); }
      sidoMapper.deleteBySido(sidoNo);
    }

    @Override
    public List<SidoDTO> findBySido() {

      List<SidoDTO> list = sidoMapper.findBySido();
      return list;
    } 
}
