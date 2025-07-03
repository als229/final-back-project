package com.kh.finalproject.content.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.content.model.dto.ContentDTO;
import com.kh.finalproject.content.model.dto.CoordinateDTO;
import com.kh.finalproject.content.model.dto.FestivalDTO;
import com.kh.finalproject.content.model.dto.FoodDTO;
import com.kh.finalproject.content.model.dto.LodgingDTO;
import com.kh.finalproject.content.model.dto.TourDTO;
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

}
