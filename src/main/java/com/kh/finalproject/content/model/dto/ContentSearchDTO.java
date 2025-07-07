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
public class ContentSearchDTO {
	
    private Long categoryCode;
    private String regionName;
    private String keyword;

}
