package com.kh.finalproject.home.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.finalproject.home.model.dao.HomeMapper;
import com.kh.finalproject.home.model.vo.HomeVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HoemServiceImpl implements HomeService{

		private final HomeMapper homeMapper;
		
	@Override
	public List<HomeVO> category() {
		List<HomeVO> categoryList= homeMapper.category();
		return categoryList;
		}
		
	@Override
	public List<HomeVO> contentType(String category) {
	
		List<HomeVO> contentTypeList = homeMapper.contentType(category);
		
		return contentTypeList;
	}
	
			
	
	
}
