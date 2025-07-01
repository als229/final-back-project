package com.kh.finalproject.global.map.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.finalproject.global.map.model.dto.DetailAddDTO;
import com.kh.finalproject.global.map.model.service.DetailAddService;
import com.kh.finalproject.util.model.dto.ResponseData;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/systm/detail")
public class DetailAddController {
  
  private final DetailAddService detailAddService;

  @GetMapping
  public ResponseEntity<ResponseData> findByDetailAdd(Long contentId) {

    DetailAddDTO detailAddDTO = detailAddService.findByDetailAdd(contentId);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("M100")
                  .message("세부사항 정보 조회 성공")
                  .items(detailAddDTO)
                  .build()
    );
  }

  @PostMapping
  public ResponseEntity<ResponseData> addByDetailAdd(@Valid @RequestBody DetailAddDTO detailAddDTO) {

    detailAddService.addByDetailAdd(detailAddDTO);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("M101")
                  .message("세부사항 정보 등록 성공")
                  .build()
    );
  }

  @PutMapping
  public ResponseEntity<ResponseData> updateByDetailAdd(@Valid @RequestBody DetailAddDTO detailAddDTO) {
    
    detailAddService.updateByDetailAdd(detailAddDTO);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("M100")
                  .message("세부사항 정보 수정 성공")
                  .build()
    );
  }

  @DeleteMapping
  public ResponseEntity<ResponseData> deleteByDetailAdd(@PathVariable Long detailAddNo) {

    detailAddService.deleteByDetailAdd(detailAddNo);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("M104")
                  .message("세부사항 정보 삭제 성공")
                  .build()
    );
  }
  
}
