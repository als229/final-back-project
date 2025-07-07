package com.kh.finalproject.content.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.finalproject.content.model.dto.ContentCardDTO;
import com.kh.finalproject.content.model.dto.DetailContentDTO;
import com.kh.finalproject.content.model.vo.Content;

@Mapper
public interface ContentMapper {
	
	Long insertContent(Content content);
	
	void updateFirstImage(@Param("contentId") Long contentId, @Param("fileUrl") String fileUrl);
	
	void updateContentImage(Content content);
	
	List<ContentCardDTO> selectContentList();
	
	void updateContent(Content content);
	
	void deleteContent(Long contentId);
	
	List<ContentCardDTO> searchContents(Long categoryCode, String regionName, String keyword);
	
	DetailContentDTO findDetailByContentId(@Param("contentId") Long contentId);

	
}
