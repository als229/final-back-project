package com.kh.finalproject.diner.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.diner.model.dto.DinerDTO;
import com.kh.finalproject.diner.model.service.DinerService;
import com.kh.finalproject.util.model.dto.ResponseData;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/diners")
public class DinerController {

  private final DinerService dinerService;

  @GetMapping
  public ResponseEntity<ResponseData> findByDiner() {

    List<?> list = dinerService.findByDiner();
    if(list.isEmpty()){
      return ResponseEntity.ok(
        ResponseData.builder()
                    .code("F104")
                    .message("음식점 목록이 없습니다.")
                    .build()
      );
    }
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("F100")
                  .message("음식점 목록 조회 성공")
                  .items(list)
                  .build()
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseData> findByDinerId(@PathVariable Long id) {

    DinerDTO dinerDTO = dinerService.findByDinerId(id);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("F100")
                  .message("음식점 상세 조회 성공")
                  .items(dinerDTO)
                  .build()
    );
  }

  @PostMapping
  public ResponseEntity<ResponseData> addByDiner(
    @Valid @RequestBody DinerDTO dinerDTO, 
    @RequestParam("files") List<MultipartFile> files
    ) {

    dinerService.addByDiner(dinerDTO, files);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("F101")
                  .message("음식점 등록 성공")
                  .build()
    );
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseData> updateByDiner(
    @PathVariable Long id, 
    @Valid @RequestBody DinerDTO dinerDTO, 
    @RequestParam("files") List<MultipartFile> files
    ) {
    
    dinerDTO.setContentId(id);
    dinerService.updateByDiner(dinerDTO, files);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("F100")
                  .message("음식점 수정 성공")
                  .items(dinerService.findByDinerId(id))
                  .build()
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseData> deleteByDiner(@PathVariable Long id) {

    dinerService.deleteByDiner(id);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("F104")
                  .message("음식점 삭제 성공")
                  .build()
    );
  } 
}
