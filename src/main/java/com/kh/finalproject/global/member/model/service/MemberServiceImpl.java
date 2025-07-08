package com.kh.finalproject.global.member.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.finalproject.global.log.model.service.LogService;
import com.kh.finalproject.global.member.model.dao.MemberMapper;
import com.kh.finalproject.global.member.model.dto.MemberDTO;
import com.kh.finalproject.global.member.model.vo.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberMapper memberMapper;
  private final LogService logService;

  @Override
  @Transactional
  public void updateByMemberNickName(MemberDTO dto) {

    MemberVO vo = MemberVO.builder()
                          .userNo(dto.getUserNo())
                          .nickName(dto.getNickName())
                          .build();
    memberMapper.updateByMemberNickName(vo);
    logService.addByUserLog(dto.getUserNo(), "회원의 닉네임(이)가","관리자의 의해 변경되었습니다.");
  } 

  @Override
  @Transactional
  public void updateByMemberStatus(MemberDTO dto) {

    MemberVO vo = MemberVO.builder()
                          .userNo(dto.getUserNo())
                          .status(dto.getStatus())
                          .build();
    memberMapper.updateByMemberStatus(vo);
    logService.addByUserLog(dto.getUserNo(), "회원의 계정(이)가", "관리자의 의해 변경되었습니다.");
  }

  @Override
  public List<MemberDTO> findByMember(String status) {

    List<MemberDTO> list = memberMapper.findByMember(status);
    return list; 
  }
}
