package com.kh.finalproject.content.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContentCardDTO {
	
    private Long contentId;
    private Long categoryCode;
    private String title;
    // private String address;
    private String firstImage;

}
