package com.kh.finalproject.global.map.model.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.finalproject.exception.exceptions.NullPointException;
import com.kh.finalproject.global.map.model.dao.DongMapper;
import com.kh.finalproject.global.map.model.dto.DongDTO;
import com.kh.finalproject.global.map.model.vo.DongVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DongServiceImpl implements DongService {

    private final DongMapper dongMapper;

    @Override
    @Transactional
    //@PreAuthorize("hasRole('ADMIN')")
    public void addByDong(DongDTO dongDTO) {

      dongMapper.addByDong(
          DongVO.builder()
                .sigunguNo(dongDTO.getSigunguNo())
                .dongName(dongDTO.getDongName())
                .build()
      );
    }

    @Override
    @Transactional
    // @PreAuthorize("hasRole('ADMIN')")
    public void updateByDong(DongDTO dongDTO) {

      dongMapper.updateByDong(
          DongVO.builder()
                .dongNo(dongDTO.getDongNo())
                .sigunguNo(dongDTO.getSigunguNo())
                .dongName(dongDTO.getDongName())
                .build()
      );
    }

    @Override
    @Transactional
    // @PreAuthorize("hasRole('ADMIN')")
    public void deleteByDong(Long dongNo) {

      if(dongNo == null) { throw new NullPointException("동 번호는 필수입니다."); }
      dongMapper.deleteByDong(dongNo);
    }
    
    @Override
    public List<DongDTO> findByDong(Long sigunguNo) {
      
      List<DongDTO> list = dongMapper.findByDong(sigunguNo);
      return list;
    }
}
