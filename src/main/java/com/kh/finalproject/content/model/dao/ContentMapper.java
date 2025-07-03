package com.kh.finalproject.content.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.finalproject.content.model.vo.Content;

@Mapper
public interface ContentMapper {
	
	Long insertContent(Content content);
	
	void updateFirstImage(@Param("contentId") Long contentId, @Param("fileUrl") String fileUrl);
	
	void updateContentImage(Content content);
	
}
