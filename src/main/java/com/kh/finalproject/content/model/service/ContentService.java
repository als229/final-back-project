package com.kh.finalproject.content.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.content.model.dto.ContentCardDTO;
import com.kh.finalproject.content.model.dto.ContentDTO;
import com.kh.finalproject.content.model.dto.UpdateContentDTO;

public interface ContentService {
	
	Long insertContent(ContentDTO content, String fileUrl);
	
	void updateFirstImage(Long contentId, String fileUrl);
	
	List<ContentCardDTO> getContentList();
	
	void updateContent(UpdateContentDTO content, String fileUrl);
	
	void deleteContent(Long contentId);
}
