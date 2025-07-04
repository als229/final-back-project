package com.kh.finalproject.favorite.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDTO {

	private Long userNo;
	private Long contentId;
	private int category;
	private boolean flag;
    private boolean likeFlag;
    private boolean bookmarkFlag;

}
