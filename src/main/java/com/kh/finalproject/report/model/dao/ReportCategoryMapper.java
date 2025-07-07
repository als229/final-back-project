package com.kh.finalproject.report.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.report.model.dto.ReportCategoryDTO;
import com.kh.finalproject.report.model.vo.ReportCategoryVO;

@Mapper
public interface ReportCategoryMapper {

  void addByReportCategory(ReportCategoryVO reportCategoryVO);

  void updateByReportCategory(ReportCategoryVO reportCategoryVO);

  void deleteByReportCategory(Long categoryNo);
  
  List<ReportCategoryDTO> findByReportCategory();
}
