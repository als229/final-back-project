package com.kh.finalproject.global.member.model.service;

import java.util.List;

import com.kh.finalproject.global.member.model.dto.MemberDTO;

public interface MemberService {

  void updateByMemberNickName(MemberDTO dto);

  void updateByMemberStatus(MemberDTO dto);

  List<MemberDTO> findByMember(String status);
}
