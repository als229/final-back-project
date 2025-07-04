package com.kh.finalproject.review.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.review.model.dto.ReviewReqDTO;
import com.kh.finalproject.review.model.dto.ReviewResDTO;

public interface ReviewService {

	void addReview(ReviewReqDTO dto, List<MultipartFile> imags);
	
	List<ReviewResDTO> getCommentsByContentId(Long contentId);
	
	void updateReview(ReviewReqDTO dto, List<MultipartFile> imags);
	
	void deleteByReviewNo(Long reviewNo);
	
	ReviewResDTO selectStarSummary(Long contentId);

}
