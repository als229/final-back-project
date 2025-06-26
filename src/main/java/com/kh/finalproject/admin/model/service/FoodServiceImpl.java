package com.kh.finalproject.admin.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.admin.model.dao.ContentMapper;
import com.kh.finalproject.admin.model.dto.FoodDTO;
import com.kh.finalproject.admin.model.vo.ContentVO;
import com.kh.finalproject.admin.model.vo.FoodVO;
import com.kh.finalproject.admin.model.vo.mapVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

  private final ContentMapper contentMapper;

  @Override
  @Transactional
  public void addByFood(FoodDTO foodDTO, List<MultipartFile> files) {

    contentMapper.addByContent(
      ContentVO.builder()
               .contentId(foodDTO.getContentId())
               .categoryCode(foodDTO.getCategoryCode())
               .title(foodDTO.getTitle())
               .firstImage(foodDTO.getFirstImage())
               .tel(foodDTO.getTel())
               .homepage(foodDTO.getHomepage())
               .playTime(foodDTO.getPlayTime())
               .build()
    );
    /* 파일 만들기 */
    contentMapper.addByFood(
      FoodVO.builder()
            .contentId(foodDTO.getContentId())
            .foodExp(foodDTO.getFoodExp())
            .mainMenu(foodDTO.getMainMenu())
            .menu(foodDTO.getMenu())
            .parking(foodDTO.getParking())
            .build()
    );
  }

  @Override
  @Transactional
  public void updateByFood(FoodDTO foodDTO) {
  }

  @Override
  @Transactional
  public void deleteByFood(Long Id) {
  }

  @Override
  public List<FoodDTO> findByFood() {
    return null;
  }

  @Override
  public FoodDTO findByFoodId(Long id) {
    return null;
  }

}
