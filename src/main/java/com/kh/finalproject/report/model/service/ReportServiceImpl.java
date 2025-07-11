package com.kh.finalproject.report.model.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.finalproject.exception.exceptions.NotFoundException;
import com.kh.finalproject.global.log.model.service.LogService;
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
  private final LogService logService;

  @Override
  @Transactional
  public void addByReport(ReportDTO reportDTO) {
    
    reportMapper.addByReport(
      ReportVO.builder()
              .reviewNo(reportDTO.getReviewNo())
              .userNo(reportDTO.getUserNo())
              .penaltyNo(reportDTO.getPenaltyNo())
              .categoryNo(reportDTO.getCategoryNo())
              .reportContent(reportDTO.getReportContent())
              .build()
    );
  }

  @Override
  @Transactional
  public void updateByReport(ReportDTO reportDTO) {

    ReportVO reportVO = ReportVO.builder()
                                .reportNo(reportDTO.getReportNo())
                                .reviewNo(reportDTO.getReviewNo())
                                .penaltyNo(reportDTO.getPenaltyNo())
                                .status(reportDTO.getStatus())
                                .build();
    reportMapper.updateByReport(reportVO);
  }

  @Override
  @Transactional
  public void deleteByReportReview(Long reviewNo) {

    Long userNo = reportMapper.findUserNoByReviewNo(reviewNo);
    reportMapper.deleteByReportReview(reviewNo);
    logService.addByUserLog(userNo,"회원의 리뷰(이)가","정책 위반으로 삭제되었습니다.");
  }

  @Override
  // @PreAuthorize("hasRole('ADMIN')")
  public List<ReportDTO> findByReport(String status) {

    List<ReportDTO> list = reportMapper.findByReport(status);
    return list;
  }

  @Override
  // @PreAuthorize("hasRole('ADMIN')")
  public ReportDTO findByReportId(Long id) {

    ReportDTO reportDTO = reportMapper.findByReportId(id);
    if(reportDTO == null) { throw new NotFoundException("해당 신고가 존재하지 않습니다."); }
    return reportDTO;
  }
}
