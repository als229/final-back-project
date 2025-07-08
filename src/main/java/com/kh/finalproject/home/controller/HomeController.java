package com.kh.finalproject.home.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.finalproject.home.model.service.HomeService;
import com.kh.finalproject.home.model.vo.HomeVO;
import com.kh.finalproject.util.model.dto.ResponseData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class HomeController {

	private final HomeService homeService;
	@GetMapping("/home")
	public ResponseEntity<ResponseData> selectList(){
		
		List<HomeVO> category = homeService.allContent(); 
		
		
		
		ResponseData response = ResponseData.builder()
											.message("리스트 반환 성공")
											.items(category)
											.code("A100")
											.build();
		
		return ResponseEntity.ok(response);
	}
	
	
	
}
