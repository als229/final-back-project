package com.kh.finalproject.report.model.service;

import java.util.List;

import com.kh.finalproject.report.model.dto.ReportCategoryDTO;

public interface ReportCategoryService {

  void addByReportCategory(ReportCategoryDTO reportCategoryDTO);

  void updateByReportCategory(ReportCategoryDTO reportCategoryDTO);

  void deleteByReportCategory(Long categoryNo);

  List<ReportCategoryDTO> findByReportCategory();
}
