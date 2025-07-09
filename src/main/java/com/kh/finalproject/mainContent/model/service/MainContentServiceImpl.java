package com.kh.finalproject.mainContent.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.auth.controller.AuthController;
import com.kh.finalproject.common.PageInfo;
import com.kh.finalproject.common.Pagination;
import com.kh.finalproject.common.S3Util;
import com.kh.finalproject.mainContent.model.dao.MainContentMapper;
import com.kh.finalproject.mainContent.model.dto.ContentSearchDTO;
import com.kh.finalproject.mainContent.model.dto.DetailDTO;
import com.kh.finalproject.mainContent.model.dto.MainContentReqDTO;
import com.kh.finalproject.mainContent.model.dto.MainContentResDTO;
import com.kh.finalproject.mainContent.model.vo.Content;
import com.kh.finalproject.mainContent.model.vo.DetailAdd;
import com.kh.finalproject.mainContent.model.vo.Festival;
import com.kh.finalproject.mainContent.model.vo.FileUrl;
import com.kh.finalproject.mainContent.model.vo.Food;
import com.kh.finalproject.mainContent.model.vo.Lodging;
import com.kh.finalproject.mainContent.model.vo.Tour;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainContentServiceImpl implements MainContentService {

    private final AuthController authController;
	
	private final MainContentMapper contentMapper;
	private final S3Util s3Util;

	@Override
	public Long addMainContent(MainContentReqDTO reqDto, List<MultipartFile> files) {
		
	    List<String> imageUrls = s3Util.upLoadFiles(files);
	    
		Content requestContent = null;
		
		requestContent = Content.builder()
				.categoryCode(reqDto.getCategoryCode())
				.title(reqDto.getTitle())
				.firstImage(imageUrls.get(0))
				.tel(reqDto.getTel())
				.homepage(reqDto.getHomepage())
				.playTime(reqDto.getPlayTime())
				.build();
		
		contentMapper.addContent(requestContent);
		
		Long contentId = contentMapper.selectContentId();
		
	    for (int i = 1; i < imageUrls.size(); i++) {
	    	
			FileUrl requestFileUrl = null;
			
			requestFileUrl = FileUrl.builder()
					.contentId(contentId)
					.fileUrl(imageUrls.get(i))
					.build();
	    	contentMapper.addContentImg(requestFileUrl);
	    }

		
		return contentId;

	}

	@Override
	public void addDetailAdd(Long contentId, MainContentReqDTO reqDto) {
		Long dongNo = contentMapper.selectDongNoByRegionName(reqDto);
		
		DetailAdd requestDetailAdd = null;
		
		requestDetailAdd = DetailAdd.builder()
				.contentId(contentId)
				.dongNo(dongNo)
				.detailName(reqDto.getDetailName())
				.postAddress(reqDto.getPostAddress())
				.build();
		
		contentMapper.addDetailAdd(requestDetailAdd);
		
	}

	@Override
	public void addContentDetail(Long contentId, MainContentReqDTO reqDto) {
		int category = reqDto.getCategoryCode();
		
		// 관광지
		if(category == 1) {
			
			Tour requestTour = null;
			
			requestTour = Tour.builder()
					.contentId(contentId)
					.tourExp(reqDto.getTourExp())
					.usetimeTour(reqDto.getUsetimeTour())
					.parking(reqDto.getParking())
					.build();
			
			contentMapper.addTour(requestTour);
		
		// 맛집
		}else if(category == 2) {
			
			Food requestFood = null;
			
			requestFood = Food.builder()
					.contentId(contentId)
					.foodExp(reqDto.getFoodExp())
					.mainMenu(reqDto.getMainMenu())
					.parking(reqDto.getParking())
					.build();
			
			contentMapper.addFood(requestFood);
		
		// 숙소
		}else if(category == 3) {
			
			Lodging requestLodging = null;
			
			requestLodging = Lodging.builder()
					.contentId(contentId)
					.lodgingExp(reqDto.getLodgingExp())
					.checkIn(reqDto.getCheckIn())
					.checkOut(reqDto.getCheckOut())
					.parking(reqDto.getParking())
					.elevator(reqDto.getElevator())
					.build();
			
			contentMapper.addLodging(requestLodging);
			
		// 축제
		}else if(category == 4) {
			
			Festival requestFestival = null;
			
			requestFestival = Festival.builder()
					.contentId(contentId)
					.program(reqDto.getProgram())
					.eventExp(reqDto.getEventExp())
					.sponsor(reqDto.getSponsor())
					.usetimeFestival(reqDto.getUsetimeFestival())
					.eventStartDate(reqDto.getEventStartDate())
					.eventEndDate(reqDto.getEventEndDate())
					.build();
			
			contentMapper.addFestival(requestFestival);
			
		}else {
			System.out.println("예외 처리 ~");
		}
		
	}

	@Override
	public Map<String, Object> selectContentCardList(ContentSearchDTO searchDto) {
		
		int carNoPerPage = 9;
		int pageSize = 5;
		int currentPage = searchDto.getPage();
		
		RowBounds rowBounds = new RowBounds((currentPage-1)*carNoPerPage,carNoPerPage);
		int totalContentNo = contentMapper.selectContentCardCount(searchDto);
		
		PageInfo pageInfo = Pagination.getPageInfo(totalContentNo, currentPage, carNoPerPage, pageSize);
		
		List<MainContentResDTO> list = contentMapper.selectContentCardList(searchDto, rowBounds);
		Map <String, Object> map = new HashMap();
		map.put("list", list);
		map.put("pageInfo", pageInfo);

		System.out.println("list 나오느냐  : " + list.toString());
		
		return map;
	}

	@Override
	public MainContentResDTO selectContentByContentId(Long contentId) {
		
		MainContentResDTO selectContent = contentMapper.selectContentByContentId(contentId);
		DetailDTO detail = null;
		
		int categoryCode = selectContent.getCategoryCode();
		
		if (categoryCode == 1) {
			detail = contentMapper.selectTourByContentId(contentId);
		} else if (categoryCode == 2) {
			detail = contentMapper.selectFoodByContentId(contentId);
		} else if (categoryCode == 3) {
			detail = contentMapper.selectLodgingByContentId(contentId);
		} else if (categoryCode == 4) {
			detail = contentMapper.selectFestivalByContentId(contentId);
		} else {
			System.out.println("selectContentByContentId 예외 일으켜야되는 곳~");
		}
		
		selectContent.setDetailDto(detail);
	
		return selectContent;
	}

}
