package com.kh.finalproject.favorite.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.finalproject.auth.model.service.AuthService;
import com.kh.finalproject.auth.vo.NwUserDetails;
import com.kh.finalproject.favorite.model.dao.FavoriteMapper;
import com.kh.finalproject.favorite.model.dto.FavoriteDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService{
	
	private final FavoriteMapper favoriteMapper;
    private final AuthService authService;
    
	@Override
	public boolean addOrDeleteFavorite(FavoriteDTO favorite) {
		boolean flag = true;
		
		NwUserDetails nwUserDetails = authService.getUserDetails();
		
		Long loginUserNo = nwUserDetails.getUserNo();

		favorite.setUserNo(loginUserNo);
		
		FavoriteDTO getFavorite = favoriteMapper.selectFavorite(favorite);
		
		if(getFavorite == null) {
			favoriteMapper.addFavorite(favorite);
		}else {
			flag = false;
			favoriteMapper.deleteFavorite(favorite);
		}
		
		return flag;
	}

	@Override
	public List<FavoriteDTO> selectFavoriteListByUserNo(FavoriteDTO favorite) {
		
		return favoriteMapper.selectFavoriteByUserNo(favorite);
	}
	
    @Override
    public FavoriteDTO selectFavoriteFlags(FavoriteDTO favorite) {
    	
		NwUserDetails nwUserDetails = authService.getUserDetails();
		
		Long loginUserNo = nwUserDetails.getUserNo();

		favorite.setUserNo(loginUserNo);
		
        return favoriteMapper.selectFavoriteFlags(favorite);
    }

}
