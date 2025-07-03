package com.kh.finalproject.content.model.service;

import com.kh.finalproject.content.model.dto.ContentDTO;

public interface ContentService {
	
	Long insertContent(ContentDTO content, String fileUrl);
	
	void updateFirstImage(Long contentId, String fileUrl);
	
	// void updateContentImage(Long contentId, String fileUrl);
	
}
