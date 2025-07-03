package com.kh.finalproject.review.model.dto;

import java.sql.Date;
import java.util.List;

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
public class ReviewResDTO {

	private Long reviewNo;
    private Long userNo;
    private Long contentId;
    private String content;
    private Date createdTime;
    private Date modifiedTime;
    private String status;
    private Double average;
    private int totalCount;
    private List<String> images;
}
