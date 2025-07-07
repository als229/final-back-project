package com.kh.finalproject.festival.controller;

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

import com.kh.finalproject.festival.model.dto.FestivalDTO;
import com.kh.finalproject.festival.model.service.FestivalService;
import com.kh.finalproject.util.model.dto.ResponseData;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/festivals")
public class FestivalController {

  private final FestivalService festivalService;

  @GetMapping
  public ResponseEntity<ResponseData> findByFestival() {

    List<?> list = festivalService.findByFestival(); 
    if(list.isEmpty()){
      return ResponseEntity.ok(
        ResponseData.builder()
                    .code("F104")
                    .message("축제 목록이 없습니다.")
                    .build()
      );
    }
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("F100")
                  .message("축제 목록 조회 성공")
                  .items(list)
                  .build()
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseData> findByLodgingId(@PathVariable Long id) {

    FestivalDTO festivalDTO = festivalService.findByFestivalId(id);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("F100")
                  .message("축제 상세 조회 성공")
                  .items(festivalDTO)
                  .build()
    );
  }

  @PostMapping
  public ResponseEntity<ResponseData> addByFestival(
    @Valid @RequestBody FestivalDTO festivalDTO, 
    @RequestParam("files") List<MultipartFile> files
    ) {

    festivalService.addByFestival(festivalDTO, files);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("F101")
                  .message("축제 등록 성공")
                  .build()
    );
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<ResponseData> updateByLodging(
    @PathVariable Long id, 
    @Valid @RequestBody FestivalDTO festivalDTO, 
    @RequestParam("files") List<MultipartFile> files
    ) {

    festivalDTO.setContentId(id);
    festivalService.updateByFestival(festivalDTO, files);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("F100")
                  .message("축제 수정 성공")
                  .items(festivalService.findByFestivalId(id))
                  .build()
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseData> deleteByFestival(@PathVariable Long id) {

    festivalService.deleteByFestival(id);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("F104")
                  .message("축제 삭제 성공")
                  .build()
    );
  }
}
