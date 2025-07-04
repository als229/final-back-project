package com.kh.finalproject.review.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
				.point(dto.getPoint())
				.build();
		
		reviewMapper.addReview(requestData);
		
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
	public void updateReview(ReviewReqDTO dto, List<MultipartFile> files) {
	    NwUserDetails userDetails = authService.getUserDetails();
	    Long userNo = userDetails.getUserNo();

	    ReviewResDTO oldReviewDTO = reviewMapper.selectReviewsByReviewNo(dto.getReviewNo());

	    if (!userNo.equals(oldReviewDTO.getUserNo())) {
	        throw new IllegalArgumentException("리뷰 수정 권한이 없습니다.");
	    }

	    List<String> originalUrls = reviewMapper.selectReviewImgsByReviewNo(dto.getReviewNo());
	    List<String> remainUrls = dto.getFileUrls();

	    List<String> toDelete = originalUrls.stream()
	        .filter(url -> !remainUrls.contains(url))
	        .toList();

	    if (!toDelete.isEmpty()) {
	        s3Util.deleteFiles(toDelete);
	        for (String url : toDelete) {
	            reviewMapper.deleteReviewImgsByfileUrl(url);
	        }
	    }

	    List<String> newImageUrls = files != null && !files.isEmpty()
	        ? s3Util.upLoadFiles(files)
	        : List.of();
	    
	    System.out.println(" newImageUrls : " + newImageUrls);

	    Review requestData = Review.builder()
	        .reviewNo(dto.getReviewNo()) // ✅ 누락되어 있던 부분
	        .content(dto.getContent())
	        .contentId(dto.getContentId())
	        .userNo(userNo)
	        .point(dto.getPoint())
	        .build();

	    reviewMapper.updateReview(requestData);

	    for (String imageUrl : newImageUrls) {
	        Map<String, Object> param = new HashMap<>();
	        param.put("reviewNo", dto.getReviewNo());
	        param.put("fileUrl", imageUrl);
	        
	        reviewMapper.addReviewImgUpdateVer(param);
	    }
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

	@Override
	public void deleteByReviewNo(Long reviewNo) {
		
	    NwUserDetails userDetails = authService.getUserDetails();
	    Long userNo = userDetails.getUserNo();

	    ReviewResDTO review = reviewMapper.selectReviewsByReviewNo(reviewNo);

	    if (!userNo.equals(review.getUserNo())) {
	        throw new IllegalArgumentException("리뷰 삭제 권한이 없습니다.");
	    }

	    List<String> imageUrls = reviewMapper.selectReviewImgsByReviewNo(reviewNo);

	    if (imageUrls != null && !imageUrls.isEmpty()) {
	        s3Util.deleteFiles(imageUrls);
	    }

	    reviewMapper.deleteReview(reviewNo);
	    reviewMapper.deleteReviewImgs(reviewNo);
		
	}






}
