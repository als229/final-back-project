package com.kh.finalproject.content.model.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ContentImage {
    Long contentImgNo;
    Long contentId;
    String fileUrl;
} 