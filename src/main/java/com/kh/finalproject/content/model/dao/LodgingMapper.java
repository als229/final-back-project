package com.kh.finalproject.content.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.content.model.dto.LodgingDTO;
import com.kh.finalproject.content.model.vo.Lodging;

@Mapper
public interface LodgingMapper {
	
	void insertLodging(Lodging lodging);
	void updateLodging(Lodging lodging);
	LodgingDTO findByContentId(Long contentId);


}
