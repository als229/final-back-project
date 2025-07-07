package com.kh.finalproject.home.model.service;

import java.util.List;

import com.kh.finalproject.home.model.vo.HomeVO;

public interface HomeService {

	
	List<HomeVO> category();
	List<HomeVO> contentType(String category);
	
}
