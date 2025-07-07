package com.kh.finalproject.tour.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.tour.model.dto.TourDTO;
import com.kh.finalproject.tour.model.service.TourService;
import com.kh.finalproject.util.model.dto.ResponseData;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/regions")
public class TourController {

  private final TourService tourService;

  @GetMapping
  public ResponseEntity<ResponseData> findByTour() {

    List<?> list = tourService.findByTour(); 
    if(list.isEmpty()){
      return ResponseEntity.ok(
        ResponseData.builder()
                    .code("T104")
                    .message("관광지 목록이 없습니다.")
                    .build()
      );
    }
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("T100")
                  .message("관광지 목록 조회 성공")
                  .items(list)
                  .build()
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseData> findByTourId(@PathVariable Long id) {

    TourDTO tourDTO = tourService.findByTourId(id);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("T100")
                  .message("관광지 상세 조회 성공")
                  .items(tourDTO)
                  .build()
    );
  }

  @PostMapping
  public ResponseEntity<ResponseData> addByTour(
    @Valid @RequestBody TourDTO tourDTO, 
    @RequestParam("files") List<MultipartFile> files
    ) {

    tourService.addByTour(tourDTO, files);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("T101")
                  .message("관광지 등록 성공")
                  .items(tourService.findByTourId(tourDTO.getContentId()))
                  .build()
    );
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<ResponseData> updateByTour(
    @PathVariable Long id, 
    @Valid @RequestBody TourDTO tourDTO, 
    @RequestParam("files") List<MultipartFile> files
    ) {

    tourDTO.setContentId(id);
    tourService.updateByTour(tourDTO, files);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("T100")
                  .message("관광지 수정 성공")
                  .items(tourService.findByTourId(id))
                  .build()
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseData> deleteByLodging(@PathVariable Long id) {

    tourService.deleteByTour(id);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("T104")
                  .message("관광지 삭제 성공")
                  .build()
    );
  }
}
