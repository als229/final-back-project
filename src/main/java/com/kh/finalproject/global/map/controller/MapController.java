package com.kh.finalproject.global.map.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.finalproject.global.map.model.dto.MapDTO;
import com.kh.finalproject.global.map.model.service.MapService;
import com.kh.finalproject.util.model.dto.ResponseData;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/systm/map")
public class MapController {

  private final MapService mapService;
  
  @GetMapping
  public ResponseEntity<ResponseData> findByMapId(@PathVariable Long id) {

    MapDTO mapDTO = mapService.findByMapId(id); 
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("M100")
                  .message("지도 정보 조회 성공")
                  .items(mapDTO)
                  .build()
    );
  }

  @PostMapping("/{id}")
  public ResponseEntity<ResponseData> addByMap(
    @PathVariable Long id,
    @Valid @RequestBody MapDTO mapDTO
    ) {
    
    mapDTO.setContentId(id);
    mapService.addByMap(mapDTO);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("M101")
                  .message("지도 정보 등록 성공")
                  .build()
    );
  } 

  @PutMapping("/{id}")
  public ResponseEntity<ResponseData> updateByMap(
    @PathVariable Long id,
    @Valid @RequestBody MapDTO mapDTO
    ) {
    
    mapDTO.setContentId(id);
    mapService.updateByMap(mapDTO);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("M100")
                  .message("지도 정보 수정 성공")
                  .build()
    );
  }

}
