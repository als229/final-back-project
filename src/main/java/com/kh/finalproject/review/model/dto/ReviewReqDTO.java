package com.kh.finalproject.review.model.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewReqDTO {
    private Long userNo;
    private Long contentId;
    private Long reviewNo;

    @NotBlank(message = "댓글 내용은 비어 있을 수 없습니다.")
    @Size(max = 300, message = "댓글 내용은 최대 300자까지 가능합니다.")
    private String content;
    private Double point;
    private List<String> fileUrls;
    
    private List<MultipartFile> images;
}
