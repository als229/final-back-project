package com.kh.finalproject.review.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.review.model.dto.ReviewReqDTO;
import com.kh.finalproject.review.model.dto.ReviewResDTO;
import com.kh.finalproject.review.model.service.ReviewService;
import com.kh.finalproject.util.model.dto.ResponseData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {
	
	private final ReviewService reviewService;
	
	@PostMapping
	public ResponseEntity<?> addReview(@ModelAttribute ReviewReqDTO review, @RequestParam(name="file", required = false) List<MultipartFile> files) {
		
		log.info("ReviewController insertReview : ReviewReqDTO 값 확인 {} , file 값 확인 {}" , review, files);
		
		reviewService.addReview(review, files);
		
		ResponseData responseData = ResponseData.builder()
				.code("A100")
				.message("댓글 등록 성공")
				.build();

		return ResponseEntity.ok(responseData);
	}
	
	@GetMapping("/summary")
	public ResponseEntity<?> selectStarSummary(@RequestParam("contentId") Long contentId){
		
		log.info("ReviewController insertReview : contentId 값 확인 {}" , contentId);
		
		ReviewResDTO resDto = reviewService.selectStarSummary(contentId);
		
		ResponseData responseData = ResponseData.builder()
				.code("A100")
				.items(resDto)
				.message("조회 성공")
				.build();
		
		return ResponseEntity.ok(responseData);
	};
	
	@GetMapping("/{contentId}")
	public ResponseEntity<ResponseData> getCommentsByContentId(@PathVariable("contentId") Long contentId) {
	    List<ReviewResDTO> comments = reviewService.getCommentsByContentId(contentId);

	    ResponseData responseData = ResponseData.builder()
	            .code("A100")
	            .items(comments)
	            .message("댓글 전체 조회 완료")
	            .build();

	    return ResponseEntity.ok(responseData);
	}
	
//	@PutMapping("/{reviewNo}")
//	public ResponseEntity<ReviewReqDTO> updateReview(ReviewDTO review,@PathVariable("reviewNo") Long reviewNo, @RequestParam(name="file", required = false) MultipartFile file){
//		review.setReviewNo(reviewNo);
//		 
//		log.info("ReviewController updateReview : review : {} , file : {}", review,file);
//		reviewService.updateReview(review, file);
//		
//	    return ResponseEntity.noContent().build();
//	}
//	
//	@DeleteMapping("/{reviewNo}")
//	public ResponseEntity<?> deleteByReviewNo(@PathVariable(name = "reviewNo") Long reviewNo){
//		
//		reviewService.deleteByReviewNo(reviewNo);
//		return ResponseEntity.noContent().build();
//	}

}
