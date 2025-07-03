package com.kh.finalproject.favorite.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	private final FavoriteService eventService;
	
	@PostMapping
	public ResponseEntity<ResponseData> addOrDeleteFavorite(@RequestBody FavoriteDTO favorite) {
		
		log.info("FavoriteController addFavorite : FavoriteDTO 값 확인 {}", favorite );
		
		eventService.addOrDeleteFavorite(favorite);
		
		ResponseData responseData = ResponseData.builder()
				.code("A100")
				.message("좋아요 등록 완료.")
				.build();
		
		return ResponseEntity.ok(responseData);
	}
	
	@GetMapping
	public ResponseEntity<ResponseData> selectFavoriteListByUserNo(@RequestBody FavoriteDTO favorite){
		
		log.info("FavoriteController selectFavoriteListByUserNo : FavoriteDTO 값 확인 {}", favorite );
		
		List<FavoriteDTO> favorites = eventService.selectFavoriteListByUserNo(favorite);
		
		ResponseData responseData = ResponseData.builder()
				.code("A100")
				.items(favorites)
				.message("리스트 조회 완료.")
				.build();
		
		return ResponseEntity.ok(responseData);
	};
	
	
}
