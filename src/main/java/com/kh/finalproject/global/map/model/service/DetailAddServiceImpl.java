package com.kh.finalproject.global.map.model.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.finalproject.exception.exceptions.NullPointException;
import com.kh.finalproject.global.map.model.dao.DetailAddMapper;
import com.kh.finalproject.global.map.model.dto.DetailAddDTO;
import com.kh.finalproject.global.map.model.vo.DetailAddVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DetailAddServiceImpl implements DetailAddService {

  private final DetailAddMapper detailAddMapper;

  @Override
  @Transactional
  @PreAuthorize("hasRole('ADMIN')")
  public void addByDetailAdd(DetailAddDTO detailAddDTO) {

    detailAddMapper.addByDetailAdd(
      DetailAddVO.builder()
                 .contentId(detailAddDTO.getContentId())
                 .dongNo(detailAddDTO.getDongNo())
                 .detailName(detailAddDTO.getDetailName())
                 .postAddress(detailAddDTO.getPostAddress())
                 .build()
    );
  }

  @Override
  @Transactional
  @PreAuthorize("hasRole('ADMIN')")
  public void updateByDetailAdd(DetailAddDTO detailAddDTO) {

    detailAddMapper.updateByDetailAdd(
      DetailAddVO.builder()
                 .contentId(detailAddDTO.getContentId())
                 .detailNo(detailAddDTO.getDetailNo())
                 .dongNo(detailAddDTO.getDongNo())
                 .detailName(detailAddDTO.getDetailName())
                 .postAddress(detailAddDTO.getPostAddress())
                 .build()
    );
  }

  @Override
  @Transactional
  @PreAuthorize("hasRole('ADMIN')")
  public void deleteByDetailAdd(Long detailAddNo) {

    if(detailAddNo == null) { throw new NullPointException("세부주소 번호는 필수입니다."); }
    detailAddMapper.deleteByDetailAdd(detailAddNo);
  }

  @Override
  public DetailAddDTO findByDetailAdd(Long contentId) {

    return detailAddMapper.findByDetailAdd(contentId);
  }
}
