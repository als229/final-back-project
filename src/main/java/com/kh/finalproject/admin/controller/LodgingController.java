package com.kh.finalproject.admin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.admin.model.dto.LodgingDTO;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@Slf4j
@RestController
@RequestMapping("/api/lodging")
public class LodgingController {
  
  @GetMapping
  public ResponseEntity<?> findByLodging() {
    return null;
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findByLodgingId(@PathVariable Long id) {
    return null;
  }

  @PostMapping
  public ResponseEntity<?> addByLodging(
    @Valid @RequestBody LodgingDTO lodgingDTO, 
    @RequestParam("files") List<MultipartFile> files
    ) {

    return null;
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<?> updateByLodging(
    @PathVariable Long id, 
    @Valid @RequestBody LodgingDTO lodgingDTO, 
    @RequestParam("files") List<MultipartFile> files
    ) {
    return null;
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteByLodging(@PathVariable Long id) {
    return null;
  }
}
