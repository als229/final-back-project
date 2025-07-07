package com.kh.finalproject.content.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.finalproject.content.model.dao.ContentMapper;
import com.kh.finalproject.content.model.dao.CoordinateMapper;
import com.kh.finalproject.content.model.dao.FestivalMapper;
import com.kh.finalproject.content.model.dao.FoodMapper;
import com.kh.finalproject.content.model.dao.LodgingMapper;
import com.kh.finalproject.content.model.dao.TourMapper;
import com.kh.finalproject.content.model.dto.ContentCardDTO;
import com.kh.finalproject.content.model.dto.ContentDTO;
import com.kh.finalproject.content.model.dto.CoordinateDTO;
import com.kh.finalproject.content.model.dto.DetailContentDTO;
import com.kh.finalproject.content.model.vo.Content;
import com.kh.finalproject.exception.exceptions.ContentException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService{
	
	private final ContentMapper contentMapper;
	private final LodgingMapper lodgingMapper;
	private final FoodMapper foodMapper;
	private final TourMapper tourMapper;
	private final FestivalMapper festivalMapper;
	private final CoordinateMapper coordinateMapper;

	@Override
	public Long insertContent(ContentDTO content, String fileUrl) {
		
		Content requestData = Content.builder()
				.categoryCode(content.getCategoryCode())
				.title(content.getTitle())
				.firstImageUrl("00000")
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
	public void updateContent(DetailContentDTO content, String fileUrl) {
	    
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
	
	@Override
	public List<ContentCardDTO> searchContents(Long categoryCode, String regionName, String keyword) {
	    return contentMapper.searchContents(categoryCode, regionName, keyword);
	}

	@Override
	public DetailContentDTO getContentDetail(Long contentId) {
	    DetailContentDTO content = contentMapper.findDetailByContentId(contentId);
	    if (content == null) {
	        throw new ContentException("해당 콘텐츠를 찾을 수 없습니다.");
	    }
	    CoordinateDTO coordinate = coordinateMapper.findByContentId(contentId);
	    DetailContentDTO detail = new DetailContentDTO();
	    detail.setContentId(content.getContentId());
	    detail.setCategoryCode(content.getCategoryCode());
	    detail.setTitle(content.getTitle());
	    detail.setTel(content.getTel());
	    detail.setHomepage(content.getHomepage());
	    detail.setPlaytime(content.getPlaytime());
	    detail.setCoordinateDTO(coordinate);
	    
	    System.out.println("DEBUG: contentid = " + content.getContentId());

	    switch (content.getCategoryCode().intValue()) {
	        case 1:
	            detail.setLodgingDTO(lodgingMapper.findByContentId(contentId));
	            break;
	        case 2:
	            detail.setFoodDTO(foodMapper.findByContentId(contentId));
	            break;
	        case 3:
	            detail.setTourDTO(tourMapper.findByContentId(contentId));
	            break;
	        case 4:
	            detail.setFestivalDTO(festivalMapper.findByContentId(contentId));
	            break;
	    }

	    return detail;
	}
	

}
