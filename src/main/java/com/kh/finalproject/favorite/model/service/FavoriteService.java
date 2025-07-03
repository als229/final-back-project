package com.kh.finalproject.favorite.model.service;

import java.util.List;

import com.kh.finalproject.favorite.model.dto.FavoriteDTO;

public interface FavoriteService {

	void addOrDeleteFavorite(FavoriteDTO favorite);
	
	List<FavoriteDTO> selectFavoriteListByUserNo(FavoriteDTO favorite);
}
