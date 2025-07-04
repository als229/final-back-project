package com.kh.finalproject.content.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.content.model.dto.ContentImgDTO;

public interface ContentImgService {
	
	String uploadImageOnly(MultipartFile file);
	
	void uploadAndInsertImages(List<MultipartFile> files, Long contentId);
	
}
