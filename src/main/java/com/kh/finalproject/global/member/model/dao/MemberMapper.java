package com.kh.finalproject.global.member.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.global.member.model.dto.MemberDTO;
import com.kh.finalproject.global.member.model.vo.MemberVO;

@Mapper
public interface MemberMapper {

  void updateByMemberNickName(MemberVO vo);

  void updateByMemberStatus(MemberVO vo);

  List<MemberDTO> findByMember(String status);
}
