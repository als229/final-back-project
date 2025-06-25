package com.kh.finalproject.report.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.finalproject.report.model.dto.ReportDTO;
import com.kh.finalproject.report.model.service.ReportService;
import com.kh.finalproject.util.model.dto.ResponseData;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/report")
public class ReportController {

  private final ReportService reportService;
  
  @GetMapping
  public ResponseEntity<ResponseData> findByReport(@RequestParam(value = "status", required = false) String status) {

    List<ReportDTO> list = reportService.findByReport(status);
    if(list.isEmpty()){
      return ResponseEntity.ok(
        ResponseData.builder()
                    .code("R104")
                    .message("신고 목록이 없습니다.")
                    .build()
      );
    }

    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("R100")
                  .message("신고 목록 조회 성공")
                  .items(list)
                  .build()
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseData> findByReportId(@PathVariable("id") Long id) {

    ReportDTO reportDTO = reportService.findByReportId(id);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("R100")
                  .items(reportDTO)
                  .message("신고 상세 조회 성공")
                  .build()
    );
  }

  @PostMapping
  public ResponseEntity<ResponseData> addByReport(@Valid @RequestBody ReportDTO reportDTO) {

    reportService.addByReport(reportDTO);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("R101")
                  .message("신고 등록 성공")
                  .build()
    );
  }
}
