package com.kh.finalproject.global.member.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.finalproject.global.member.model.dto.MemberDTO;
import com.kh.finalproject.global.member.model.service.MemberService;
import com.kh.finalproject.util.model.dto.ResponseData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/systm/member")
public class MemberController {
  
  private final MemberService memberService;

  @GetMapping
  public ResponseEntity<ResponseData> findByMember(@RequestParam(value = "status", required = false) String status) {

    List<MemberDTO> list = memberService.findByMember(status);
    if(list.isEmpty()){
      return ResponseEntity.ok(
        ResponseData.builder()
                    .code("M103")
                    .message("권한이 부족하여 조회할 수 없습니다.")
                    .build()
      );
    }
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("M100")
                  .message("회원 목록 조회 성공")
                  .items(list)
                  .build()
    );
  }

  @PutMapping("/nickName")
  public ResponseEntity<ResponseData> updateByMemberNickName(@RequestBody MemberDTO dto) {
    
    memberService.updateByMemberNickName(dto);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("M101")
                  .message("회원 닉네임 수정 성공")
                  .build()
    );
  }

  @PutMapping("/status")
  public ResponseEntity<ResponseData> updateByMemberStatus(@RequestBody MemberDTO dto) {
    
    memberService.updateByMemberStatus(dto);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("M101")
                  .message("회원 상태 수정 성공")
                  .build()
    );
  }
}
