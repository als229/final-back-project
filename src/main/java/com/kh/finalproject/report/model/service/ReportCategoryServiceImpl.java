package com.kh.finalproject.report.model.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.finalproject.exception.exceptions.NullPointException;
import com.kh.finalproject.report.model.dao.ReportCategoryMapper;
import com.kh.finalproject.report.model.dto.ReportCategoryDTO;
import com.kh.finalproject.report.model.vo.ReportCategoryVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportCategoryServiceImpl implements ReportCategoryService {

  private final ReportCategoryMapper reportCategoryMapper;

  @Override
  @Transactional
  // @PreAuthorize("hasRole('ADMIN')")
  public void addByReportCategory(ReportCategoryDTO reportCategoryDTO) {

    reportCategoryMapper.addByReportCategory(
        ReportCategoryVO.builder()
                        .categoryName(reportCategoryDTO.getCategoryName())
                        .build()
    );
  }

  @Override
  @Transactional
  // @PreAuthorize("hasRole('ADMIN')")
  public void updateByReportCategory(ReportCategoryDTO reportCategoryDTO) {

    reportCategoryMapper.updateByReportCategory(
        ReportCategoryVO.builder()
                        .categoryNo(reportCategoryDTO.getCategoryNo())
                        .categoryName(reportCategoryDTO.getCategoryName())
                        .build()
    );
  }

  @Override
  @Transactional
  // @PreAuthorize("hasRole('ADMIN')")
  public void deleteByReportCategory(Long categoryNo) {

    if(categoryNo == null) { throw new NullPointException("카테고리 번호는 필수입니다."); }
    reportCategoryMapper.deleteByReportCategory(categoryNo);
  }

  @Override
  public List<ReportCategoryDTO> findByReportCategory() {

    List<ReportCategoryDTO> list = reportCategoryMapper.findByReportCategory();
    return list; 
  }
}
