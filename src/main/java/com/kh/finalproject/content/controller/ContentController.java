package com.kh.finalproject.content.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.content.model.dto.ContentCardDTO;
import com.kh.finalproject.content.model.dto.ContentDTO;
import com.kh.finalproject.content.model.dto.CoordinateDTO;
import com.kh.finalproject.content.model.dto.FestivalDTO;
import com.kh.finalproject.content.model.dto.FoodDTO;
import com.kh.finalproject.content.model.dto.LodgingDTO;
import com.kh.finalproject.content.model.dto.TourDTO;
import com.kh.finalproject.content.model.dto.UpdateContentDTO;
import com.kh.finalproject.content.model.service.ContentImgService;
import com.kh.finalproject.content.model.service.ContentService;
import com.kh.finalproject.content.model.service.CoordinateService;
import com.kh.finalproject.content.model.service.FestivalService;
import com.kh.finalproject.content.model.service.FoodService;
import com.kh.finalproject.content.model.service.LodgingService;
import com.kh.finalproject.content.model.service.TourService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/content")
public class ContentController {
	
	private final ContentService contentService;
	private final CoordinateService coordinateService;
	private final LodgingService lodgingService;
	private final TourService tourService;
	private final FoodService foodService;
	private final FestivalService festivalService;
	private final ContentImgService contentImgService;
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	// @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> insertContent(
			@RequestPart("contentDTO") ContentDTO contentDTO,
			@RequestPart(value = "firstImage", required = false) MultipartFile firstImage,
			@RequestPart(value = "images", required = false) List<MultipartFile> images
	) {
		
		Long contentId = contentService.insertContent(contentDTO, null);

	    if (firstImage != null && !firstImage.isEmpty()) {
	        String fileUrl = contentImgService.uploadImageOnly(firstImage);
	        contentService.updateFirstImage(contentId, fileUrl);
	    }
	    
	    if (images != null && !images.isEmpty()) {
	        contentImgService.uploadAndInsertImages(images, contentId);
	    }

		CoordinateDTO coordinateDTO = contentDTO.getCoordinateDTO();
		if (coordinateDTO != null) {
			coordinateService.insertCoordinate(coordinateDTO, contentId);
		}

		switch (contentDTO.getCategoryCode() != null ? contentDTO.getCategoryCode().intValue() : -1) {
			case 1:
				LodgingDTO lodgingDTO = contentDTO.getLodgingDTO();
				if (lodgingDTO != null) {
					lodgingService.insertLodging(lodgingDTO, contentId);
				}
				break;
			case 2:
				FoodDTO foodDTO = contentDTO.getFoodDTO();
				if (foodDTO != null) {
					foodService.insertFood(foodDTO, contentId);
				}
				break;
			case 3:
				TourDTO tourDTO = contentDTO.getTourDTO();
				if (tourDTO != null) {
					tourService.insertTour(tourDTO, contentId);
				}
				break;
			case 4:
				FestivalDTO festivalDTO = contentDTO.getFestivalDTO();
				if (festivalDTO != null) {
					festivalService.insertFestival(festivalDTO, contentId);
				}
				break;
			default:
				return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/simple-list")
	public ResponseEntity<List<ContentCardDTO>> getContentList() {
	    return ResponseEntity.ok(contentService.getContentList());
	}
	
	@PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	// @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> updateContent(
	        @RequestPart("updateContentDTO") UpdateContentDTO updateContentDTO,
	        @RequestPart(value = "firstImage", required = false) MultipartFile firstImage,
	        @RequestPart(value = "images", required = false) List<MultipartFile> images
	) {
	    
	    Long contentId = updateContentDTO.getContentId();
	    
	    contentService.updateContent(updateContentDTO, null);

	    if (firstImage != null && !firstImage.isEmpty()) {
	        String fileUrl = contentImgService.uploadImageOnly(firstImage);
	        contentService.updateFirstImage(contentId, fileUrl);
	    }
	    
	    if (images != null && !images.isEmpty()) {
	        // 기존 이미지들 삭제 (필요하다면)
	        // contentImgService.deleteImagesByContentId(contentId);
	        contentImgService.uploadAndInsertImages(images, contentId);
	    }

	    CoordinateDTO coordinateDTO = updateContentDTO.getCoordinateDTO();
	    if (coordinateDTO != null) {
	        coordinateService.updateCoordinate(coordinateDTO, contentId);
	    }

	    switch (updateContentDTO.getCategoryCode() != null ? updateContentDTO.getCategoryCode().intValue() : -1) {
	        case 1:
	            LodgingDTO lodgingDTO = updateContentDTO.getLodgingDTO();
	            if (lodgingDTO != null) {
	                lodgingService.updateLodging(lodgingDTO, contentId);
	            }
	            break;
	        case 2:
	            FoodDTO foodDTO = updateContentDTO.getFoodDTO();
	            if (foodDTO != null) {
	                foodService.updateFood(foodDTO, contentId);
	            }
	            break;
	        case 3:
	            TourDTO tourDTO = updateContentDTO.getTourDTO();
	            if (tourDTO != null) {
	                tourService.updateTour(tourDTO, contentId);
	            }
	            break;
	        case 4:
	            FestivalDTO festivalDTO = updateContentDTO.getFestivalDTO();
	            if (festivalDTO != null) {
	                festivalService.updateFestival(festivalDTO, contentId);
	            }
	            break;
	        default:
	            return ResponseEntity.badRequest().build();
	    }

	    return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{contentId}")
	public ResponseEntity<Void> deleteContent(@PathVariable Long contentId) {
	    contentService.deleteContent(contentId);
	    return ResponseEntity.ok().build();
	}
	
	

}
