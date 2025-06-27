package com.kh.finalproject.global.log.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.global.log.model.dto.UserLogDTO;
import com.kh.finalproject.global.log.model.vo.UserLogVO;

@Mapper
public interface LogMapper {

  void addByUserLog(UserLogVO userLogVO);

  List<UserLogDTO> findByUserLog(Long userNo);
}
