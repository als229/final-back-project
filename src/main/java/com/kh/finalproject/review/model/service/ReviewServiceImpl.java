package com.kh.finalproject.review.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.auth.model.service.AuthService;
import com.kh.finalproject.auth.vo.NwUserDetails;
import com.kh.finalproject.common.S3Util;
import com.kh.finalproject.review.model.dao.ReviewMapper;
import com.kh.finalproject.review.model.dto.ReviewReqDTO;
import com.kh.finalproject.review.model.dto.ReviewResDTO;
import com.kh.finalproject.review.model.vo.Review;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
	
	private final ReviewMapper reviewMapper;
	private final AuthService authService;
	private final S3Util s3Util;

	@Override
	public void addReview(ReviewReqDTO dto, List<MultipartFile> images) {
		
		NwUserDetails nwUserDetails = authService.getUserDetails();
		
		Long loginUserNo = nwUserDetails.getUserNo();
		dto.setUserNo(loginUserNo);
		
		Review requestData = null;
		
		requestData = Review.builder()
				.content(dto.getContent())
				.contentId(dto.getContentId())
				.userNo(loginUserNo)
				.build();
		
		reviewMapper.addReview(requestData);
		
		reviewMapper.addStar(dto);

		if (images != null && !images.isEmpty()) {
		    List<String> imageUrls = s3Util.upLoadFiles(images);
		
		    for (String imageUrl : imageUrls) {
		        reviewMapper.addReviewImg(imageUrl);
		    }
		}
	 
	}
	
	@Override
	public List<ReviewResDTO> getCommentsByContentId(Long contentId) {
		
		List<ReviewResDTO> resList = reviewMapper.selectReviewsWithImages(contentId);
		
		return resList;
	}
	
	
	@Override
	public ReviewResDTO selectStarSummary(Long contentId) {
		
		int totalCount = reviewMapper.selectAllReviewCount(contentId);
		double average = reviewMapper.selectAverageStarByContentId(contentId);
		List<String> images = reviewMapper.selectAllReviewImgs(contentId);
		
		ReviewResDTO resDTO = new ReviewResDTO();
		resDTO.setTotalCount(totalCount);
		resDTO.setAverage(average);
		resDTO.setImages(images);
		
		return resDTO;
	}




}
