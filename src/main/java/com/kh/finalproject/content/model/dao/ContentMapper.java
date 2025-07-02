package com.kh.finalproject.content.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.content.model.vo.Content;

@Mapper
public interface ContentMapper {
	
	Long insertContent(Content content);
	
}
