package com.kh.finalproject.favorite.model.service;

import java.util.List;

import com.kh.finalproject.favorite.model.dto.FavoriteDTO;


public interface FavoriteService {

	boolean addOrDeleteFavorite(FavoriteDTO favorite);
	
	List<FavoriteDTO> selectFavoriteListByUserNo(FavoriteDTO favorite);
	
	FavoriteDTO selectFavoriteFlags(FavoriteDTO favorite);
}
