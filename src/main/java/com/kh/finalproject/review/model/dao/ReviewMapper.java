package com.kh.finalproject.review.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.finalproject.review.model.dto.ReviewReqDTO;
import com.kh.finalproject.review.model.dto.ReviewResDTO;
import com.kh.finalproject.review.model.dto.StarDTO;
import com.kh.finalproject.review.model.vo.Review;
import com.kh.finalproject.review.model.vo.ReviewImg;

@Mapper
public interface ReviewMapper {
    int addReview(Review review);
    
    List<ReviewResDTO> selectReviewByContentNo(Long reviewNo);
    
    int updateReview(Review review);
    
    int deleteReview(Long reviewNo);
    
    // 이미지 관련    
    int addReviewImg(String reviewImg);

    List<String> selectAllReviewImgs(Long contentId);

    List<String> selectReviewImgsByReviewNo(Long reviewNo);
    
    List<ReviewResDTO> selectReviewsWithImages(Long contentId);

    int deleteReviewImgsByReviewNo(Long reviewNo);
    
    // 별점 관련
    int addStar(ReviewReqDTO dto);
    
    int updateStar(StarDTO star);

    StarDTO selectStarByUserAndContent(StarDTO star);

    Double selectAverageStarByContentId(Long contentId);

    int selectAllReviewCount(Long contentId);
    
}
