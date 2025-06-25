package com.kh.finalproject.log.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.finalproject.log.model.dao.LogMapper;
import com.kh.finalproject.log.model.dto.UserLogDTO;
import com.kh.finalproject.log.model.vo.UserLogVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogService {

  private final LogMapper logMapper;

  @Transactional
  private void addByUserLog(UserLogDTO userLogDTO){

    logMapper.addByUserLog(
      UserLogVO.builder()
               .userNo(userLogDTO.getUserNo())
               .oldInfo(userLogDTO.getOldInfo())
               .newInfo(userLogDTO.getNewInfo())
               .build());
  }

  /**
   * 회원 로그 추가.
   *
   * @param userNo    회원 번호 - !조회 필수
   * @param oldInfo   변경 전 정보 - not null
   * @param newInfo   변경 후 정보 - not null
   */
  public void addByUserLog(Long userNo, String oldInfo, String newInfo) {

    UserLogDTO userLogDTO = new UserLogDTO();
    userLogDTO.setUserNo(userNo);
    userLogDTO.setOldInfo(oldInfo);
    userLogDTO.setNewInfo(newInfo);
    addByUserLog(userLogDTO);
  }

  public List<UserLogDTO> findByUserLog(Long userNo) {
    
    List<UserLogDTO> list = logMapper.findByUserLog(userNo);
    return list;
  }
}
