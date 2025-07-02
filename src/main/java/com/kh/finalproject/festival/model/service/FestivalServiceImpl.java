package com.kh.finalproject.festival.model.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.exception.exceptions.NotFoundException;
import com.kh.finalproject.festival.model.dao.FestivalMapper;
import com.kh.finalproject.festival.model.dto.FestivalDTO;
import com.kh.finalproject.festival.model.vo.FestivalVO;
import com.kh.finalproject.global.model.dao.ContentMapper;
import com.kh.finalproject.global.model.dto.ContentDTO;
import com.kh.finalproject.global.model.vo.ContentVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FestivalServiceImpl implements FestivalService {

  private final ContentMapper contentMapper;
  private final FestivalMapper festivalMapper;

  @Override
  @Transactional
  @PreAuthorize("hasRole('ADMIN')")
  public void addByFestival(FestivalDTO festivalDTO, List<MultipartFile> files) {

    ContentVO contentVO = ContentVO.builder()
                                   .categoryCode(festivalDTO.getCategoryCode())
                                   .title(festivalDTO.getTitle())
                                   .firstImage(festivalDTO.getFirstImage())
                                   .tel(festivalDTO.getTel())
                                   .homepage(festivalDTO.getHomepage())
                                   .playTime(festivalDTO.getPlayTime())
                                   .build();
    contentMapper.addByContent(contentVO);

    Long contentId = contentVO.getContentId();
    festivalMapper.addByFestival(
      FestivalVO.builder()
                .contentId(contentId)
                .program(festivalDTO.getProgram())
                .eventExp(festivalDTO.getEventExp())
                .sponsor(festivalDTO.getSponsor())
                .usetimeFestival(festivalDTO.getUsetimeFestival())
                .eventStartDate(festivalDTO.getEventStartDate())
                .eventEndDate(festivalDTO.getEventEndDate())
                .build()
    );
  }

  @Override
  @Transactional
  @PreAuthorize("hasRole('ADMIN')")
  public void updateByFestival(FestivalDTO festivalDTO, List<MultipartFile> files) {

    contentMapper.updateByContent(
      ContentVO.builder()
               .contentId(festivalDTO.getContentId())
               .categoryCode(festivalDTO.getCategoryCode())
               .title(festivalDTO.getTitle())
               .firstImage(festivalDTO.getFirstImage())
               .tel(festivalDTO.getTel())
               .homepage(festivalDTO.getHomepage())
               .playTime(festivalDTO.getPlayTime())
               .build()
    );
    festivalMapper.updateByFestival(
      FestivalVO.builder()
                .contentId(festivalDTO.getContentId())
                .program(festivalDTO.getProgram())
                .eventExp(festivalDTO.getEventExp())
                .sponsor(festivalDTO.getSponsor())
                .usetimeFestival(festivalDTO.getUsetimeFestival())
                .eventStartDate(festivalDTO.getEventStartDate())
                .eventEndDate(festivalDTO.getEventEndDate())
                .build()
    );
  }   

  @Override
  @Transactional
  @PreAuthorize("hasRole('ADMIN')")
  public void deleteByFestival(Long id) {

    contentMapper.deleteByContent(id);
  }

  @Override
  public List<?> findByFestival() {

    List<ContentDTO> list = contentMapper.findByContent(1L);
    return list; 
  }

  @Override
  public FestivalDTO findByFestivalId(Long id) {

    FestivalDTO festivalDTO = festivalMapper.findByFestivalId(id);
    if(festivalDTO == null) { throw new NotFoundException("해당 축제가 존재하지 않습니다."); }
    return festivalDTO; 
  }
}
