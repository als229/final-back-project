package com.kh.finalproject.mainContent.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.mainContent.model.dto.ContentSearchDTO;
import com.kh.finalproject.mainContent.model.dto.MainContentReqDTO;
import com.kh.finalproject.mainContent.model.dto.MainContentResDTO;

public interface MainContentService {
	
	public Long addMainContent(MainContentReqDTO reqDto, List<MultipartFile> files);
	
	public void addDetailAdd(Long contentId, MainContentReqDTO reqDto);

	public void addContentDetail(Long contentId, MainContentReqDTO reqDto);
	
	public Map<String, Object> selectContentCardList(ContentSearchDTO searchDto);
	
}

