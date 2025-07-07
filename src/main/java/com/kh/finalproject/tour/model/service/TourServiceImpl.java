package com.kh.finalproject.tour.model.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.exception.exceptions.NotFoundException;
import com.kh.finalproject.global.model.dao.ContentMapper;
import com.kh.finalproject.global.model.dto.ContentDTO;
import com.kh.finalproject.global.model.vo.ContentVO;
import com.kh.finalproject.tour.model.dao.TourMapper;
import com.kh.finalproject.tour.model.dto.TourDTO;
import com.kh.finalproject.tour.model.vo.TourVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

  private final ContentMapper contentMapper;
  private final TourMapper tourMapper;

  @Override
  @Transactional
  // @PreAuthorize("hasRole('ADMIN')")
  public void addByTour(TourDTO tourDTO, List<MultipartFile> files) {

    ContentVO contentVO = ContentVO.builder()
                                   .categoryCode(tourDTO.getCategoryCode())
                                   .title(tourDTO.getTitle())
                                   .firstImage(tourDTO.getFirstImage())
                                   .tel(tourDTO.getTel())
                                   .homepage(tourDTO.getHomepage())
                                   .playTime(tourDTO.getPlayTime())
                                   .build();
    contentMapper.addByContent(contentVO);

    Long contentId = contentVO.getContentId();
    tourMapper.addByTour(
      TourVO.builder()
            .contentId(contentId)
            .tourExp(tourDTO.getTourExp())
            .usetimeTour(tourDTO.getUsetimeTour())
            .parking(tourDTO.getParking())
            .build()
    );
  }

  @Override
  @Transactional
  // @PreAuthorize("hasRole('ADMIN')")
  public void updateByTour(TourDTO tourDTO, List<MultipartFile> files) {

    contentMapper.updateByContent(
      ContentVO.builder()
               .contentId(tourDTO.getContentId())
               .categoryCode(tourDTO.getCategoryCode())
               .title(tourDTO.getTitle())
               .firstImage(tourDTO.getFirstImage())
               .tel(tourDTO.getTel())
               .homepage(tourDTO.getHomepage())
               .playTime(tourDTO.getPlayTime())
               .build()
    );
    tourMapper.updateByTour(
      TourVO.builder()
            .contentId(tourDTO.getContentId())
            .tourExp(tourDTO.getTourExp())
            .usetimeTour(tourDTO.getUsetimeTour())
            .parking(tourDTO.getParking())
            .build()
    );
  }

  @Override
  @Transactional
  // @PreAuthorize("hasRole('ADMIN')")
  public void deleteByTour(Long id) {

    contentMapper.deleteByContent(id);
  }

  @Override
  public List<?> findByTour() {

    List<ContentDTO> list = contentMapper.findByContent(4L);
    return list;
  }

  @Override
  public TourDTO findByTourId(Long id) {

    TourDTO tourDTO = tourMapper.findByTourId(id);
    if(tourDTO == null) { throw new NotFoundException("해당 관광지가 존재하지 않습니다."); }
    return tourDTO;
  }
}
