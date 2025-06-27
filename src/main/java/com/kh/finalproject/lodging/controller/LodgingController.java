package com.kh.finalproject.lodging.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.lodging.model.dto.LodgingDTO;
import com.kh.finalproject.lodging.model.service.LodgingService;
import com.kh.finalproject.util.model.dto.ResponseData;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/lodgings")
public class LodgingController {

  private final LodgingService lodgingService; 
  
  @GetMapping
  public ResponseEntity<ResponseData> findByLodging() {

    List<?> list = lodgingService.findByLodging(); 
    if(list.isEmpty()){
      return ResponseEntity.ok(
        ResponseData.builder()
                    .code("L104")
                    .message("숙박 목록이 없습니다.")
                    .build()
      );
    }
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("L100")
                  .message("숙박 목록 조회 성공")
                  .items(list)
                  .build()
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseData> findByLodgingId(@PathVariable Long id) {

    LodgingDTO lodgingDTO = lodgingService.findByLodgingId(id);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("L100")
                  .message("숙박 상세 조회 성공")
                  .items(lodgingDTO)
                  .build()
    );
  }

  @PostMapping
  public ResponseEntity<ResponseData> addByLodging(
    @Valid @RequestBody LodgingDTO lodgingDTO, 
    @RequestParam("files") List<MultipartFile> files
    ) {

    lodgingService.addByLodging(lodgingDTO, files);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("L101")
                  .message("숙박 등록 성공")
                  .build()
    );
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<ResponseData> updateByLodging(
    @PathVariable Long id, 
    @Valid @RequestBody LodgingDTO lodgingDTO, 
    @RequestParam("files") List<MultipartFile> files
    ) {

    lodgingDTO.setContentId(id);
    lodgingService.updateByLodging(lodgingDTO, files);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("L100")
                  .message("숙박 수정 성공")
                  .items(lodgingService.findByLodgingId(id))
                  .build()
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseData> deleteByLodging(@PathVariable Long id) {

    lodgingService.deleteByLodging(id);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("L104")
                  .message("숙박 삭제 성공")
                  .build()
    );
  }
}
