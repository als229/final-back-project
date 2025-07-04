package com.kh.finalproject.content.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.finalproject.content.model.dao.ContentMapper;
import com.kh.finalproject.content.model.dto.ContentCardDTO;
import com.kh.finalproject.content.model.dto.ContentDTO;
import com.kh.finalproject.content.model.dto.UpdateContentDTO;
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
				.firstImageUrl(fileUrl)
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
	
	@Override
	public List<ContentCardDTO> getContentList() {
	    return contentMapper.selectContentList();
	}
	
	@Override
	public void updateContent(UpdateContentDTO content, String fileUrl) {
	    
	    Content requestData = Content.builder()
	            .contentId(content.getContentId())  // UpdateContentDTO에서 가져옴
	            .categoryCode(content.getCategoryCode())
	            .title(content.getTitle())
	            .firstImageUrl("557575")  // 나중에 fileUrl로 변경하실 거니까
	            .tel(content.getTel())
	            .homepage(content.getHomepage())
	            .playtime(content.getPlaytime())
	            .build();

	    contentMapper.updateContent(requestData);
	}
	
	@Override
	public void deleteContent(Long contentId) {
		contentMapper.deleteContent(contentId);
	}

	

}
