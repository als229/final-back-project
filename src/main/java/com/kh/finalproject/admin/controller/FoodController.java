package com.kh.finalproject.admin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.admin.model.dto.FoodDTO;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@RestController
@RequestMapping("/api/food")
public class FoodController {

  @GetMapping
  public ResponseEntity<?> findByFood(FoodDTO foodDTO, List<MultipartFile> files) {
    return null;
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findByFoodId(@PathVariable Long id) {
    return null;
  }

  @PostMapping
  public ResponseEntity<?/*ResponseData*/> addByFood(@Valid @RequestBody FoodDTO foodDTO, @RequestParam("files") List<MultipartFile> files) {
    return null;
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateByFood(@PathVariable Long id, @Valid @RequestBody FoodDTO foodDTO, @RequestParam("files") List<MultipartFile> files) {

    return null;
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteByFood(@PathVariable Long id) {

    return null;
  }
  
}
