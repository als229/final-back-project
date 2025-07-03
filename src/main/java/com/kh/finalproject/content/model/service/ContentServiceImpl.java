package com.kh.finalproject.content.model.service;

import org.springframework.stereotype.Service;

import com.kh.finalproject.content.model.dao.ContentMapper;
import com.kh.finalproject.content.model.dto.ContentDTO;
import com.kh.finalproject.content.model.vo.Content;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService{
	
	private final ContentMapper contentMapper;

	@Override
	public Long insertContent(ContentDTO content, String fileUrl) {
		
		Content requestData = Content.builder()
				.categoryCode(content.getCategoryCode())
				.title(content.getTitle())
				.firstImageUrl("557575")
				.tel(content.getTel())
				.homepage(content.getHomepage())
				.playtime(content.getPlaytime())
				.build();
		
		contentMapper.insertContent(requestData);
		
		Long contentId = requestData.getContentId();
		System.out.println(requestData);
		return contentId;
	}
	
	@Override
	public void updateFirstImage(Long contentId, String fileUrl) {
	    contentMapper.updateFirstImage(contentId, fileUrl);
	}
	
	/*
	@Override
	public void updateContentImage(Long contentId, String fileUrl) {
	    Content content = Content.builder()
	            .contentId(contentId)
	            .firstImageUrl(fileUrl)
	            .build();
	    contentMapper.updateContentImage(content);
	}
	*/
	
	

}
