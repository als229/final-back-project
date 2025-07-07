package com.kh.finalproject.report.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.finalproject.report.model.dto.ReportCategoryDTO;
import com.kh.finalproject.report.model.service.ReportCategoryService;
import com.kh.finalproject.util.model.dto.ResponseData;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/systm/reportCategorys")
public class ReportCategoryController {

  private final ReportCategoryService reportCategoryService;
  
  @GetMapping
  public ResponseEntity<ResponseData> findByReportCategory() {

    List<ReportCategoryDTO> list = reportCategoryService.findByReportCategory();
    if( list.isEmpty()) {
      return ResponseEntity.ok(
        ResponseData.builder()
                    .code("R104")
                    .message("카테고리 조회 결과가 없습니다.")
                    .build()
      );
    }
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("R100")
                  .message("카테고리 조회 성공")
                  .items(list)
                  .build()
    );
  }
  
  @PostMapping
  public ResponseEntity<ResponseData> addByReportCategory(@Valid @RequestBody ReportCategoryDTO reportCategoryDTO) {

    reportCategoryService.addByReportCategory(reportCategoryDTO);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("R101")
                  .message("카테고리 등록 성공")
                  .build()
    );
  }

  @PutMapping
  public ResponseEntity<ResponseData> updateByReportCategory(@Valid @RequestBody ReportCategoryDTO reportCategoryDTO) {
    
    reportCategoryService.updateByReportCategory(reportCategoryDTO);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("R100")
                  .message("카테고리 수정 성공")
                  .build()
    );
  }

  @DeleteMapping
  public ResponseEntity<ResponseData> deleteByReportCategory(@PathVariable Long categoryNo) {

    reportCategoryService.deleteByReportCategory(categoryNo);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("R104")
                  .message("카테고리 삭제 성공")
                  .build()
    );
  }
}
