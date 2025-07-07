package com.kh.finalproject.global.map.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.finalproject.global.map.model.dto.DongDTO;
import com.kh.finalproject.global.map.model.service.DongService;
import com.kh.finalproject.util.model.dto.ResponseData;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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
@RequestMapping("/api/systm/dong")
public class DongController {
  
  private final DongService dongService;

  @GetMapping
  public ResponseEntity<ResponseData> findByDong(@RequestParam Long sigunguNo) {

    List<DongDTO> list = dongService.findByDong(sigunguNo);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("M100")
                  .message("동 정보 조회 성공")
                  .items(list)
                  .build()
    );
  }

  @PostMapping
  public ResponseEntity<ResponseData> addByDong(@Valid @RequestBody DongDTO dongDTO) {

    dongService.addByDong(dongDTO);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("M101")
                  .message("동 정보 등록 성공")
                  .build()
    );
  }

  @PutMapping("/{dongNo}")
  public ResponseEntity<ResponseData> updateByDong(
    @PathVariable Long dongNo,
    @Valid @RequestBody DongDTO dongDTO
    ) {
    
    dongDTO.setDongNo(dongNo);
    dongService.updateByDong(dongDTO);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("M100")
                  .message("동 정보 수정 성공")
                  .build()
    );
  }

  @DeleteMapping("/{dongNo}")
  public ResponseEntity<ResponseData> deleteByDong(@PathVariable Long dongNo) {

    dongService.deleteByDong(dongNo);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("M104")
                  .message("동 정보 삭제 성공")
                  .build()
    );
  }
  
}
