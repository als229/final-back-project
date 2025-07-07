package com.kh.finalproject.content.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.content.model.dto.FestivalDTO;
import com.kh.finalproject.content.model.vo.Festival;

@Mapper
public interface FestivalMapper {
	
	void insertFestival(Festival festival);
	void updateFestival(Festival festival);
	FestivalDTO findByContentId(Long contentId);


}
