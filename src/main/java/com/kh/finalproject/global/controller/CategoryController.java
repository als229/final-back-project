package com.kh.finalproject.global.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.finalproject.global.model.dto.CategoryDTO;
import com.kh.finalproject.global.model.service.CategoryService;
import com.kh.finalproject.util.model.dto.ResponseData;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/system/categories")
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  public ResponseEntity<ResponseData> findByCategory() {
    
    List<CategoryDTO> list = categoryService.findByCategory();
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("C100")
                  .message("카테고리 조회 성공")
                  .items(list)
                  .build()
    );
  }

  @PostMapping
  public ResponseEntity<ResponseData> addByCategory(
    @Valid @RequestBody CategoryDTO categoryDTO
  ) {
    
    categoryService.addByCategory(categoryDTO);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("C101")
                  .message("카테고리 추가 성공")
                  .build()
    );
  }

  @PutMapping
  public ResponseEntity<ResponseData> updateByCategory(
    @PathVariable Long categoryCode,
    @Valid @RequestBody CategoryDTO categoryDTO
  ) {
    
    categoryDTO.setCategoryCode(categoryCode);
    categoryService.updateByCategory(categoryDTO);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("C100")
                  .message("카테고리 수정 성공")
                  .build()
    );
  }
  @DeleteMapping
  public ResponseEntity<ResponseData> deleteByCategory(@PathVariable Long categoryCode) {
    
    categoryService.deleteByCategory(categoryCode);
    return ResponseEntity.ok(
      ResponseData.builder()
                  .code("C104")
                  .message("카테고리 삭제 성공")
                  .build()
    );
  }
}
