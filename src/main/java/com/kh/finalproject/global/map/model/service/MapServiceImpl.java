package com.kh.finalproject.global.map.model.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.finalproject.global.map.model.dao.MapMapper;
import com.kh.finalproject.global.map.model.dto.MapDTO;
import com.kh.finalproject.global.map.model.vo.MapVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MapServiceImpl implements MapService {

  private final MapMapper mapMapper;
  
  @Override
  @Transactional
  // @PreAuthorize("hasRole('ADMIN')")
  public void addByMap(MapDTO mapDTO) {
    
    mapMapper.addByMap(
      MapVO.builder()
           .contentId(mapDTO.getContentId())
           .mapX(mapDTO.getMapX())
           .mapY(mapDTO.getMapY())
           .build()
    );
  }

  @Override
  @Transactional
  // @PreAuthorize("hasRole('ADMIN')")
  public void updateByMap(MapDTO mapDTO) {
    
    mapMapper.updateByMap(
      MapVO.builder()
           .contentId(mapDTO.getContentId())
           .mapX(mapDTO.getMapX())
           .mapY(mapDTO.getMapY())
           .build());
  }

  @Override
  public MapDTO findByMapId(Long contentId) {

    return mapMapper.findByMapId(contentId);
  }
}
