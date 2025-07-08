package com.kh.finalproject.mainContent.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.common.PageInfo;
import com.kh.finalproject.mainContent.model.dto.ContentSearchDTO;
import com.kh.finalproject.mainContent.model.dto.MainContentReqDTO;
import com.kh.finalproject.mainContent.model.dto.MainContentResDTO;
import com.kh.finalproject.mainContent.model.service.MainContentService;
import com.kh.finalproject.util.model.dto.ResponseData;

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
	public ResponseEntity<ResponseData> addContent(@ModelAttribute MainContentReqDTO reqDto, @RequestParam(name="file", required = false) List<MultipartFile> files) {
		
		log.info("MainContentController addContent : MainContentReqDTO 값 확인 {} , file 값 확인 {}" , reqDto, files);
		
		Long contentId = mainService.addMainContent(reqDto, files);
		
		ResponseData responseData = ResponseData.builder()
				.code("A100")
				.items(contentId)
				.message("컨텐츠, 이미지 등록 성공")
				.build();

		return ResponseEntity.ok(responseData);
	}
	
    @PostMapping("/{contentId}/address")
    public ResponseEntity<ResponseData> addAddress(
            @PathVariable("contentId") Long contentId,
            @ModelAttribute MainContentReqDTO reqDto
    ) {
        log.info("addAddress: contentId={}, reqDto={}", contentId, reqDto);

        mainService.addDetailAdd(contentId, reqDto);

        ResponseData response = ResponseData.builder()
                .code("A200")
                .message("주소 정보 저장 성공")
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{contentId}/details")
    public ResponseEntity<ResponseData> addDetails(
            @PathVariable("contentId") Long contentId,
            @ModelAttribute MainContentReqDTO reqDto
    ) {
        log.info("addDetails: contentId={}, detailData={}", contentId, reqDto);

        mainService.addContentDetail(contentId, reqDto);

        ResponseData response = ResponseData.builder()
                .code("A300")
                .message("상세 데이터 저장 성공")
                .build();
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<ResponseData> selectContentCardList(
    	    @RequestParam(name="page",           defaultValue="1")  int page,
    	    @RequestParam(name="category",       defaultValue="0")  int category,
    	    @RequestParam(name="sidoNo",         defaultValue="0")  int sidoNo,
    	    @RequestParam(name="searchKeyword",  defaultValue="")   String searchKeyword
    		) {
    	
    	ContentSearchDTO searchDto = new ContentSearchDTO(page, category, sidoNo, searchKeyword);
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
    	
    	log.info("selectContentCardList: contentId={}", contentId);
    	
    	MainContentResDTO resDto = mainService.selectContentByContentId(contentId);
    	
    	ResponseData response = ResponseData.builder()
    			.code("A400")
    			.items(resDto)
    			.message("상세 데이터 조회 성공")
    			.build();
    	
    	return ResponseEntity.ok(response);
    }
    
}
