package com.kh.finalproject.favorite.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.favorite.model.dto.FavoriteDTO;

@Mapper
public interface FavoriteMapper {

	void addFavorite(FavoriteDTO favorite);
	
	void deleteFavorite(FavoriteDTO favorite);
	
	FavoriteDTO selectFavorite(FavoriteDTO favorite);
	
	List<FavoriteDTO> selectFavoriteByUserNo(FavoriteDTO userNo);
	
}
