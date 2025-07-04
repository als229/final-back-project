package com.kh.finalproject.content.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.content.model.vo.ContentImg;

@Mapper
public interface ContentImgMapper {
	
	void insertContentImg(ContentImg contentImg);

}
