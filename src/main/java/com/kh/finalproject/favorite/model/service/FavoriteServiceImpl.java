package com.kh.finalproject.favorite.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.finalproject.favorite.model.dao.FavoriteMapper;
import com.kh.finalproject.favorite.model.dto.FavoriteDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService{
	
	private final FavoriteMapper favoriteMapper;

	@Override
	public void addOrDeleteFavorite(FavoriteDTO favorite) {
		
		FavoriteDTO getFavorite = favoriteMapper.selectFavorite(favorite);
		
		if(getFavorite == null) {
			favoriteMapper.addFavorite(favorite);
		}else {
			favoriteMapper.deleteFavorite(favorite);
		}
	}

	@Override
	public List<FavoriteDTO> selectFavoriteListByUserNo(FavoriteDTO favorite) {
		
		return favoriteMapper.selectFavoriteByUserNo(favorite);
	}

}
