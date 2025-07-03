package com.kh.finalproject.diner.model.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.diner.model.dao.DinerMapper;
import com.kh.finalproject.diner.model.dto.DinerDTO;
import com.kh.finalproject.diner.model.vo.DinerVO;
import com.kh.finalproject.exception.exceptions.NotFoundException;
import com.kh.finalproject.global.model.dao.ContentMapper;
import com.kh.finalproject.global.model.dto.ContentDTO;
import com.kh.finalproject.global.model.vo.ContentVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DinerServiceImpl implements DinerService {

  private final ContentMapper contentMapper;
  private final DinerMapper dinerMapper;

  @Override
  @Transactional
  // @PreAuthorize("hasRole('ADMIN')")
  public void addByDiner(DinerDTO dinerDTO, List<MultipartFile> files) {

    ContentVO contentVO = ContentVO.builder()
                                   .categoryCode(dinerDTO.getCategoryCode())
                                   .title(dinerDTO.getTitle())
                                   .firstImage(dinerDTO.getFirstImage())
                                   .tel(dinerDTO.getTel())
                                   .homepage(dinerDTO.getHomepage())
                                   .playTime(dinerDTO.getPlayTime())
                                   .build();
    contentMapper.addByContent(contentVO);

    Long contentId = contentVO.getContentId();
    dinerMapper.addByDiner(
      DinerVO.builder()
            .contentId(contentId)
            .foodExp(dinerDTO.getFoodExp())
            .mainMenu(dinerDTO.getMainMenu())
            .menu(dinerDTO.getMenu())
            .parking(dinerDTO.getParking())
            .build()
    );
  }

  @Override
  @Transactional
  // @PreAuthorize("hasRole('ADMIN')")
  public void updateByDiner(DinerDTO dinerDTO, List<MultipartFile> files) {

    contentMapper.updateByContent(
      ContentVO.builder()
               .contentId(dinerDTO.getContentId())
               .categoryCode(dinerDTO.getCategoryCode())
               .title(dinerDTO.getTitle())
               .firstImage(dinerDTO.getFirstImage())
               .tel(dinerDTO.getTel())
               .homepage(dinerDTO.getHomepage())
               .playTime(dinerDTO.getPlayTime())
               .build()
    );
    dinerMapper.updateByDiner(
      DinerVO.builder()
            .contentId(dinerDTO.getContentId())
            .foodExp(dinerDTO.getFoodExp())
            .mainMenu(dinerDTO.getMainMenu())
            .menu(dinerDTO.getMenu())
            .parking(dinerDTO.getParking())
            .build()
    );
  }

  @Override
  @Transactional
  // @PreAuthorize("hasRole('ADMIN')")
  public void deleteByDiner(Long Id) {
    contentMapper.deleteByContent(Id);
  }

  @Override
  public List<?> findByDiner() {

    List<ContentDTO> list = contentMapper.findByContent(2L);
    return list;
  }

  @Override
  public DinerDTO findByDinerId(Long id) {

    DinerDTO dinerDTO = dinerMapper.findByDinerId(id);
    if (dinerDTO == null) { throw new NotFoundException("해당 음식점이 존재하지 않습니다."); }
    return dinerDTO;
  }

}
