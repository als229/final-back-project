package com.kh.finalproject.mainContent.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.mainContent.model.dto.ContentSearchDTO;
import com.kh.finalproject.mainContent.model.dto.MainContentReqDTO;
import com.kh.finalproject.mainContent.model.dto.MainContentResDTO;

import jakarta.validation.Valid;

public interface MainContentService {
	
	public void addMainContent(MainContentReqDTO reqDto, List<MultipartFile> files,MultipartFile thumbnail);
	
	public Map<String, Object> selectContentCardList(ContentSearchDTO searchDto);
	
	public MainContentResDTO selectContentByContentId(Long contentId);

	public void updateContent(Long contentId, @Valid MainContentReqDTO dto, MultipartFile thumbnail, List<MultipartFile> files, String thumbnailUrl, List<String> deletedImages);

	public void deleteContentByContentId(Long contentId, String status);
	
}

