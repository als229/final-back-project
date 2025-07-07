package com.kh.finalproject.global.map.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.finalproject.global.map.model.dto.SigunguDTO;
import com.kh.finalproject.global.map.model.service.SigunguService;
import com.kh.finalproject.util.model.dto.ResponseData;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/systm/sigungu")
public class SigunguController {
  
  private final SigunguService sigunguService;

  @GetMapping
  public ResponseEntity<ResponseData> findBySigungu(Long sidoNo) {

    List<SigunguDTO> list = sigunguService.findBySigungu(sidoNo);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("M100")
                  .message("시군구 정보 조회 성공")
                  .items(list)
                  .build()
    );
  }

  @PostMapping
  public ResponseEntity<ResponseData> addBySido(@Valid @RequestBody SigunguDTO sigunguDTO) {

    sigunguService.addBySigungu(sigunguDTO);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("M101")
                  .message("시군구 정보 등록 성공")
                  .build()
    );
  }

  @PutMapping
  public ResponseEntity<ResponseData> updateBySido(@Valid @RequestBody SigunguDTO sigunguDTO) {
    
    sigunguService.updateBySigungu(sigunguDTO);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("M100")
                  .message("시군구 정보 수정 성공")
                  .build()
    );
  }

  @DeleteMapping
  public ResponseEntity<ResponseData> deleteBySido(@PathVariable Long sigunguNo) {

    sigunguService.deleteBySigungu(sigunguNo);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("M104")
                  .message("시군구 정보 삭제 성공")
                  .build()
    );
  }
}
