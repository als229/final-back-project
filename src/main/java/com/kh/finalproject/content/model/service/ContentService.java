package com.kh.finalproject.content.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.content.model.dto.ContentCardDTO;
import com.kh.finalproject.content.model.dto.ContentDTO;
import com.kh.finalproject.content.model.dto.DetailContentDTO;

public interface ContentService {
	
	Long insertContent(ContentDTO content, String fileUrl);
	
	void updateFirstImage(Long contentId, String fileUrl);
	
	List<ContentCardDTO> getContentList();
	
	void updateContent(DetailContentDTO content, String fileUrl);
	
	void deleteContent(Long contentId);
	
	List<ContentCardDTO> searchContents(Long categoryCode, String regionName, String keyword);
	
	DetailContentDTO getContentDetail(Long contentId);
}
