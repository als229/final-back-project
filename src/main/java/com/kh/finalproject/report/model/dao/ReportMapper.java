package com.kh.finalproject.report.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.finalproject.report.model.dto.ReportDTO;
import com.kh.finalproject.report.model.vo.ReportVO;

@Mapper
public interface ReportMapper {

  void addByReport(ReportVO reportVO);

  List<ReportDTO> findByReport(String status);

  ReportDTO findByReportId(Long id);
}
