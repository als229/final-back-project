package com.kh.finalproject.global.map.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.finalproject.global.map.model.dto.DetailViewDTO;
import com.kh.finalproject.global.map.model.service.DetailViewService;
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
public class DetailViewController {
  
  private final DetailViewService detailViewService;

  @GetMapping
  public ResponseEntity<ResponseData> findByDetailView(Long contentId) {

    DetailViewDTO detailViewDTO = detailViewService.findByDetailView(contentId);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("M100")
                  .message("세부사항 정보 조회 성공")
                  .items(detailViewDTO)
                  .build()
    );
  }

  @PostMapping
  public ResponseEntity<ResponseData> addByDetailAView(@Valid @RequestBody DetailViewDTO detailViewDTO) {

    detailViewService.addByDetailView(detailViewDTO);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("M101")
                  .message("세부사항 정보 등록 성공")
                  .build()
    );
  }

  @PutMapping
  public ResponseEntity<ResponseData> updateByDetailView(@Valid @RequestBody DetailViewDTO detailViewDTO) {
    
    detailViewService.updateByDetailView(detailViewDTO);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("M100")
                  .message("세부사항 정보 수정 성공")
                  .build()
    );
  }

  @DeleteMapping
  public ResponseEntity<ResponseData> deleteByDetailView(@PathVariable Long detailViewNo) {

    detailViewService.deleteByDetailView(detailViewNo);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("M104")
                  .message("세부사항 정보 삭제 성공")
                  .build()
    );
  }
  
}
