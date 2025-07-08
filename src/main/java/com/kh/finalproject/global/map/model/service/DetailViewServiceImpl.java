package com.kh.finalproject.global.map.model.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.finalproject.exception.exceptions.NullPointException;
import com.kh.finalproject.global.map.model.dao.DetailViewMapper;
import com.kh.finalproject.global.map.model.dto.DetailViewDTO;
import com.kh.finalproject.global.map.model.vo.DetailViewVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DetailViewServiceImpl implements DetailViewService {

  private final DetailViewMapper detailViewMapper;

  @Override
  @Transactional
  // @PreAuthorize("hasRole('ADMIN')")
  public void addByDetailView(DetailViewDTO detailViewDTO) {

    detailViewMapper.addByDetailView(
      DetailViewVO.builder()
                 .contentId(detailViewDTO.getContentId())
                 .dongNo(detailViewDTO.getDongNo())
                 .detailName(detailViewDTO.getDetailName())
                 .postAddress(detailViewDTO.getPostAddress())
                 .build()
    );
  }

  @Override
  @Transactional
  // @PreAuthorize("hasRole('ADMIN')")
  public void updateByDetailView(DetailViewDTO detailViewDTO) {

    detailViewMapper.updateByDetailView(
      DetailViewVO.builder()
                 .contentId(detailViewDTO.getContentId())
                 .detailNo(detailViewDTO.getDetailNo())
                 .dongNo(detailViewDTO.getDongNo())
                 .detailName(detailViewDTO.getDetailName())
                 .postAddress(detailViewDTO.getPostAddress())
                 .build()
    );
  }

  @Override
  @Transactional
  // @PreAuthorize("hasRole('ADMIN')")
  public void deleteByDetailView(Long detailViewNo) {

    if(detailViewNo == null) { throw new NullPointException("세부주소 번호는 필수입니다."); }
    detailViewMapper.deleteByDetailView(detailViewNo);
  }

  @Override
  public DetailViewDTO findByDetailView(Long contentId) {

    return detailViewMapper.findByDetailView(contentId);
  }
}
