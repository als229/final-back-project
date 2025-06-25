package com.kh.finalproject.report.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.finalproject.auth.vo.NwUserDetails;
import com.kh.finalproject.exception.exceptions.NotFoundException;
import com.kh.finalproject.report.model.dao.ReportMapper;
import com.kh.finalproject.report.model.dto.ReportDTO;
import com.kh.finalproject.report.model.vo.ReportVO;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

  private final ReportMapper reportMapper;
  // private final NwUserDetails nwUserDetails;

  @Override
  @Transactional
  public void addByReport(ReportDTO reportDTO) {

    // Long userNo = nwUserDetails.getUserNo();
    reportMapper.addByReport(
      ReportVO.builder()
              .reviewNo(reportDTO.getReviewNo())
              .userNo(null)
              .penaltyNo(reportDTO.getPenaltyNo())
              .categoryNo(reportDTO.getCategoryNo())
              .reportContent(reportDTO.getReportContent())
              .build()
    );
  }

  @Override
  public List<ReportDTO> findByReport(String status) {

    List<ReportDTO> list = reportMapper.findByReport(status);
    return list;
  }

  @Override
  public ReportDTO findByReportId(Long id) {

    ReportDTO reportDTO = reportMapper.findByReportId(id);    
    if(reportDTO == null) { 
      throw new NotFoundException("해당 신고가 존재하지 않습니다."); 
    }
    return reportDTO;
  }
}
