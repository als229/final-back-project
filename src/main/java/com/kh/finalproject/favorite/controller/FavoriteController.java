package com.kh.finalproject.favorite.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.finalproject.favorite.model.dto.FavoriteDTO;
import com.kh.finalproject.favorite.model.service.FavoriteService;
import com.kh.finalproject.util.model.dto.ResponseData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/favorites")
public class FavoriteController {

	//private final FavoriteService eventService;
	
	@PostMapping
	public ResponseEntity<ResponseData> addFavorite(FavoriteDTO favorite) {
		
		log.info("FavoriteController insertFavorite : FavoriteDTO 값 확인 {}", favorite );
		
		//eventService.insertFavorite(favorite);
		
		ResponseData responseData = ResponseData.builder()
				.code("A100")
				.message("좋아요 등록 완료.")
				.build();
		
		return ResponseEntity.ok(responseData);
	}
	
}
