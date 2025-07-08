package com.kh.finalproject.report.model.service;

import java.util.List;

import com.kh.finalproject.report.model.dto.ReportDTO;

public interface ReportService {

  void addByReport(ReportDTO reportDTO);

  void updateByReport(ReportDTO reportDTO);

  void deleteByReportReview(Long reviewNo);

  List<ReportDTO> findByReport(String status);

  ReportDTO findByReportId(Long id);
}
