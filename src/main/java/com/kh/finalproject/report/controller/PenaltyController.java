package com.kh.finalproject.report.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.finalproject.report.model.dto.PenaltyDTO;
import com.kh.finalproject.report.model.service.PenaltyService;
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
@RequestMapping("/api/systm/penaltys")
public class PenaltyController {

  private final PenaltyService penaltyService;
  
  @GetMapping
  public ResponseEntity<ResponseData> findByPenalty() {
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("R100")
                  .message("페널티 조회 성공")
                  .items(penaltyService.findByPenalty())
                  .build()
    );
  }
  
@PostMapping
  public ResponseEntity<ResponseData> addByPenalty(@Valid @RequestBody PenaltyDTO penaltyDTO) {

    penaltyService.addByPenalty(penaltyDTO);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("R101")
                  .message("페널티 등록 성공")
                  .build()
    );
  }

  @PutMapping
  public ResponseEntity<ResponseData> updateByPenalty(@Valid @RequestBody PenaltyDTO penaltyDTO) {
    
    penaltyService.updateByPenalty(penaltyDTO);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("R100")
                  .message("페널티 수정 성공")
                  .build()
    );
  }

  @DeleteMapping
  public ResponseEntity<ResponseData> deleteByPenalty(@PathVariable Long penaltyNo) {

    penaltyService.deleteByPenalty(penaltyNo);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("R104")
                  .message("페널티 삭제 성공")
                  .build()
    );
  }
}
