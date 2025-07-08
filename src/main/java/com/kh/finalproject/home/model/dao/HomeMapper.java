package com.kh.finalproject.home.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.home.model.vo.HomeVO;


@Mapper
public interface HomeMapper {

	
	List<HomeVO> allContent();




}
