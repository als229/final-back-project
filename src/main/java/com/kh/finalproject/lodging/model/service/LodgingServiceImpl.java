package com.kh.finalproject.lodging.model.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.exception.exceptions.NotFoundException;
import com.kh.finalproject.global.model.dao.ContentMapper;
import com.kh.finalproject.global.model.dto.ContentDTO;
import com.kh.finalproject.global.model.vo.ContentVO;
import com.kh.finalproject.lodging.model.dao.LodgingMapper;
import com.kh.finalproject.lodging.model.dto.LodgingDTO;
import com.kh.finalproject.lodging.model.vo.LodgingVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LodgingServiceImpl implements LodgingService {

  private final ContentMapper contentMapper;
  private final LodgingMapper lodgingMapper;

  @Override
  @Transactional
  @PreAuthorize("hasRole('ADMIN')")
  public void addByLodging(LodgingDTO lodgingDTO, List<MultipartFile> files) {

    ContentVO contentVO = ContentVO.builder()
                                   .categoryCode(lodgingDTO.getCategoryCode())
                                   .title(lodgingDTO.getTitle())
                                   .firstImage(lodgingDTO.getFirstImage())
                                   .tel(lodgingDTO.getTel())
                                   .homepage(lodgingDTO.getHomepage())
                                   .playTime(lodgingDTO.getPlayTime())
                                   .build();   
    contentMapper.addByContent(contentVO);

    Long contentId = contentVO.getContentId();    
    lodgingMapper.addByLodging(
      LodgingVO.builder()
               .contentId(contentId)
               .lodgingExp(lodgingDTO.getLodgingExp())
               .checkIn(lodgingDTO.getCheckIn())
               .checkOut(lodgingDTO.getCheckOut())
               .parking(lodgingDTO.getParking())
               .elevator(lodgingDTO.getElevator())
               .build()
    );
  }

  @Override
  @Transactional
  @PreAuthorize("hasRole('ADMIN')")
  public void updateByLodging(LodgingDTO lodgingDTO, List<MultipartFile> files) {

    contentMapper.updateByContent(
      ContentVO.builder()
               .contentId(lodgingDTO.getContentId())
               .categoryCode(lodgingDTO.getCategoryCode())
               .title(lodgingDTO.getTitle())
               .firstImage(lodgingDTO.getFirstImage())
               .tel(lodgingDTO.getTel())
               .homepage(lodgingDTO.getHomepage())
               .playTime(lodgingDTO.getPlayTime())
               .build()
    );
    lodgingMapper.updateByLodging(
      LodgingVO.builder()
               .contentId(lodgingDTO.getContentId())
               .lodgingExp(lodgingDTO.getLodgingExp())
               .checkIn(lodgingDTO.getCheckIn())
               .checkOut(lodgingDTO.getCheckOut())
               .parking(lodgingDTO.getParking())
               .elevator(lodgingDTO.getElevator())
               .build()
    );
  }

  @Override
  @Transactional
  @PreAuthorize("hasRole('ADMIN')")
  public void deleteByLodging(Long id) {

    contentMapper.deleteByContent(id);
  }

  @Override
  public List<?> findByLodging() {

    List<ContentDTO> list = contentMapper.findByContent(1L);
    return list; 
  }

  @Override
  public LodgingDTO findByLodgingId(Long id) {

    LodgingDTO lodgingDTO = lodgingMapper.findByLodgingId(id);
    if(lodgingDTO == null) { throw new NotFoundException("해당 숙소가 존재하지 않습니다."); }
    return lodgingDTO;
  }

}
