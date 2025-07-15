package com.kh.finalproject.mainContent.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.common.PageInfo;
import com.kh.finalproject.mainContent.model.dto.ContentSearchDTO;
import com.kh.finalproject.mainContent.model.dto.MainContentReqDTO;
import com.kh.finalproject.mainContent.model.dto.MainContentResDTO;
import com.kh.finalproject.mainContent.model.service.MainContentService;
import com.kh.finalproject.util.model.dto.ResponseData;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/main-contents")
public class MainContentController {

	private final MainContentService mainService;
	
	@PostMapping
	public ResponseEntity<?> addContent(
	    @RequestPart("contentReqDTO") @Valid MainContentReqDTO  contentReqDTO,
	    @RequestPart(value = "thumbnail", required=true) MultipartFile thumbnail,
	    @RequestPart(value = "files", required = true) List<MultipartFile> files) {
	    
		log.info("MainContentController addContent : MainContentReqDTO 값 확인 {} , files 값 확인 {} thumbnail 값 확인 {} " , contentReqDTO, files, thumbnail);
		
		mainService.addMainContent(contentReqDTO, files, thumbnail);
		
		ResponseData responseData = ResponseData.builder()
				.code("C100")
				.message("컨텐츠 등록 성공")
				.build();

		return ResponseEntity.ok(responseData);
	}
	
    @GetMapping
    public ResponseEntity<ResponseData> selectContentCardList(
    	    @RequestParam(name="page",           defaultValue="1")  int page,
    	    @RequestParam(name="category",       defaultValue="0")  int category,
    	    @RequestParam(name="sidoNo",         defaultValue="0")  int sidoNo,
    	    @RequestParam(name="searchKeyword",  defaultValue="")   String searchKeyword,
    	    @RequestParam(name="status",  defaultValue="")   String status
    		) {
    	
    	ContentSearchDTO searchDto = new ContentSearchDTO(page, category, sidoNo, searchKeyword,status);
    	log.info("selectContentCardList: searchDto={}", searchDto);
    	
    	Map<String, Object> map = mainService.selectContentCardList(searchDto);
    	
    	ResponseData response = ResponseData.builder()
    			.code("A400")
    			.items(map.get("list"))
    			.pageInfo((PageInfo) map.get("pageInfo"))
    			.message("상세 데이터 저장 성공")
    			.build();
    	
    	return ResponseEntity.ok(response);
    }
	
    @GetMapping("{contentId}")
    public ResponseEntity<ResponseData> selectContentByContentId(
    	    @RequestParam(name="contentId") Long contentId
    		) {
    	
    	log.info("selectContentByContentId: contentId={}", contentId);
    	
    	MainContentResDTO resDto = mainService.selectContentByContentId(contentId);
    	
    	ResponseData response = ResponseData.builder()
    			.code("A400")
    			.items(resDto)
    			.message("상세 데이터 조회 성공")
    			.build();
    	
    	return ResponseEntity.ok(response);
    }
    
    @PutMapping(value = "/{contentId}")
    public ResponseEntity<ResponseData> updateContent(
            @PathVariable("contentId") Long contentId,
            @RequestPart("contentReqDTO") @Valid MainContentReqDTO dto,
            @RequestParam(value = "thumbnailUrl", required = false) String thumbnailUrl,
    	    @RequestPart(value = "thumbnail", required=false) MultipartFile thumbnail,
    	    @RequestParam(value = "deletedImages", required=false) List<String> deletedImages,
    	    @RequestPart(value = "files", required = false) List<MultipartFile> files) {
    	
		log.info("MainContentController updateContent : MainContentReqDTO 값 확인 {} , files 값 확인 {} thumbnail 값 확인 {} , thumbnailUrl 값 확인 : {}, deletedImages 값 확인 : {}" , dto, files, thumbnail, thumbnailUrl, deletedImages);
    	
    	mainService.updateContent(contentId, dto, thumbnail, files, thumbnailUrl, deletedImages);
    	
    	ResponseData response = ResponseData.builder()
    			.code("A400")
    			.message("컨텐츠 업데이트 성공")
    			.build();
    	
    	return ResponseEntity.ok(response);
    }
    
	@DeleteMapping
	public ResponseEntity<ResponseData> deleteContentByContentId(@RequestParam("contentId") Long contentId, @RequestParam("status") String status){
		
		mainService.deleteContentByContentId(contentId, status);
		
	    ResponseData responseData = ResponseData.builder()
	            .code("A200")
	            .message("컨텐츠 비활성화 성공")
	            .build();
		
	    return ResponseEntity.ok(responseData);
	}
}
