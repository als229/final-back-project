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
import com.kh.finalproject.exception.exceptions.DatabaseException;
import com.kh.finalproject.exception.exceptions.FileUploadException;
import com.kh.finalproject.exception.exceptions.InvalidRequestException;
import com.kh.finalproject.exception.exceptions.ServiceException;
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
import com.kh.finalproject.mainContent.model.vo.MapXY;
import com.kh.finalproject.mainContent.model.vo.Tour;

import jakarta.validation.Valid;
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
	public void addMainContent(MainContentReqDTO reqDto, List<MultipartFile> files, MultipartFile thumbnail) {
	    try {
	    	// 썸네일이 없거나 비어있는지 체크
	    	if (thumbnail == null || thumbnail.isEmpty()) {
	    		throw new InvalidRequestException("최소 1개 이상의 썸네일 파일이 필요합니다.");
	    	}
	        
	        // 파일이 없거나 비어있는지 체크
	        if (files == null || files.isEmpty()) {
	            throw new InvalidRequestException("최소 1개 이상의 이미지 파일이 필요합니다.");
	        }
	        
	        // 썸네일 업로드
	        String thumbnailImg = null;
	        try {
	        	thumbnailImg = s3Util.upLoadFiles(thumbnail);
	        	if (thumbnailImg.isEmpty()) {
	        		throw new FileUploadException("썸네일 업로드에 실패했습니다.");
	        	}
	        } catch (Exception e) {
	        	log.error("파일 업로드 중 오류 발생: {}", e.getMessage());
	        	throw new FileUploadException("파일 업로드 중 오류가 발생했습니다: " + e.getMessage());
	        }
	        
	        // S3 파일 업로드 (예외 처리 포함)
	        List<String> imageUrls;
	        try {
	            imageUrls = s3Util.upLoadFiles(files);
	            if (imageUrls.isEmpty()) {
	                throw new FileUploadException("파일 업로드에 실패했습니다.");
	            }
	        } catch (Exception e) {
	            log.error("파일 업로드 중 오류 발생: {}", e.getMessage());
	            throw new FileUploadException("파일 업로드 중 오류가 발생했습니다: " + e.getMessage());
	        }
	        
	        // 컨텐츠 기본 정보 저장
	        Content requestContent = null;
	        try {
	            requestContent = Content.builder()
	                    .categoryCode(reqDto.getCategoryCode())
	                    .title(reqDto.getTitle())
	                    .firstImage(thumbnailImg)
	                    .tel(reqDto.getTel())
	                    .homepage(reqDto.getHomepage())
	                    .playTime(reqDto.getPlayTime())
	                    .build();
	            
	            contentMapper.addContent(requestContent);
	        } catch (Exception e) {
	            log.error("컨텐츠 기본 정보 저장 중 오류: {}", e.getMessage());
	            throw new DatabaseException("컨텐츠 정보 저장 중 오류가 발생했습니다: " + e.getMessage());
	        }
	        
	        // 컨텐츠 ID 조회
	        Long contentId;
	        try {
	            contentId = contentMapper.selectContentId();
	            if (contentId == null || contentId <= 0) {
	                throw new DatabaseException("생성된 컨텐츠 ID를 조회할 수 없습니다.");
	            }
	        } catch (Exception e) {
	            log.error("컨텐츠 ID 조회 중 오류: {}", e.getMessage());
	            throw new DatabaseException("컨텐츠 ID 조회 중 오류가 발생했습니다: " + e.getMessage());
	        }
	        
	        // 추가 이미지 URL 저장
	        try {
	            for (int i = 0; i < imageUrls.size(); i++) {
	                FileUrl requestFileUrl = FileUrl.builder()
	                        .contentId(contentId)
	                        .fileUrl(imageUrls.get(i))
	                        .build();
	                contentMapper.addContentImg(requestFileUrl);
	            }
	        } catch (Exception e) {
	            log.error("추가 이미지 URL 저장 중 오류: {}", e.getMessage());
	            throw new DatabaseException("추가 이미지 저장 중 오류가 발생했습니다: " + e.getMessage());
	        }
	        
	        // 지역 정보 및 상세 주소 정보 저장
	        try {
	            // 지역 정보 유효성 검사
	            if (reqDto.getSidoName() == null || reqDto.getSigunguName() == null) {
	                throw new InvalidRequestException("지역 정보(시/도, 시/군/구)가 필요합니다.");
	            }
	            
	            Long dongNo = contentMapper.selectDongNoByRegionName(reqDto);
	            if (dongNo == null) {
	                throw new InvalidRequestException("입력한 지역 정보를 찾을 수 없습니다.");
	            }
	            
	            DetailAdd requestDetailAdd = DetailAdd.builder()
	                    .contentId(contentId)
	                    .dongNo(dongNo)
	                    .detailName(reqDto.getDetailName())
	                    .postAddress(reqDto.getPostAddress())
	                    .build();
	            
	            contentMapper.addDetailAdd(requestDetailAdd);
	        } catch (InvalidRequestException e) {
	            throw e;  // 이미 적절한 예외이므로 그대로 전달
	        } catch (Exception e) {
	            log.error("주소 정보 저장 중 오류: {}", e.getMessage());
	            throw new DatabaseException("주소 정보 저장 중 오류가 발생했습니다: " + e.getMessage());
	        }
	        
	        // X,Y 좌표 테이블 저장
	        try {
	            if (reqDto.getMapX() == null || reqDto.getMapY() == null) {
	                throw new InvalidRequestException("X,Y 좌표 값이 없습니다.");
	            }
	            
	            MapXY requestMapXY = MapXY.builder()
	            		.contentId(contentId)
	            		.mapX(reqDto.getMapX())
	            		.mapY(reqDto.getMapY())
	            		.build();
	            		
	            contentMapper.addMapXY(requestMapXY);
	        } catch (InvalidRequestException e) {
	            throw e; 
	        } catch (Exception e) {
	            log.error("X,Y 좌표 저장 도중 오류: {}", e.getMessage());
	            throw new DatabaseException("X,Y 좌표 저장 중 오류가 발생했습니다: " + e.getMessage());
	        }
	        
	        // 카테고리별 상세 정보 저장
	        int category = reqDto.getCategoryCode();
	        
	        try {
	            switch (category) {
	                // 관광지
	                case 1:
	                    Tour requestTour = Tour.builder()
	                            .contentId(contentId)
	                            .tourExp(reqDto.getTourExp())
	                            .usetimeTour(reqDto.getUseTimeTour())
	                            .parking(reqDto.getParking())
	                            .build();
	                    contentMapper.addTour(requestTour);
	                    break;
	                    
	                // 맛집
	                case 2:
	                    Food requestFood = Food.builder()
	                            .contentId(contentId)
	                            .foodExp(reqDto.getFoodExp())
	                            .mainMenu(reqDto.getMainMenu())
	                            .parking(reqDto.getParking())
	                            .build();
	                    contentMapper.addFood(requestFood);
	                    break;
	                    
	                // 숙소
	                case 3:
	                    Lodging requestLodging = Lodging.builder()
	                            .contentId(contentId)
	                            .lodgingExp(reqDto.getLodgingExp())
	                            .checkIn(reqDto.getCheckIn())
	                            .checkOut(reqDto.getCheckOut())
	                            .parking(reqDto.getParking())
	                            .elevator(reqDto.getElevator())
	                            .build();
	                    contentMapper.addLodging(requestLodging);
	                    break;
	                    
	                // 축제
	                case 4:
	                    Festival requestFestival = Festival.builder()
	                            .contentId(contentId)
	                            .program(reqDto.getProgram())
	                            .eventExp(reqDto.getEventExp())
	                            .sponsor(reqDto.getSponsor())
	                            .useTimeFestival(reqDto.getUseTimeFestival())
	                            .eventStartDate(reqDto.getEventStartDate())
	                            .eventEndDate(reqDto.getEventEndDate())
	                            .build();
	                    contentMapper.addFestival(requestFestival);
	                    break;
	                    
	                default:
	                    throw new InvalidRequestException("유효하지 않은 카테고리 코드입니다: " + category);
	            }
	        } catch (InvalidRequestException e) {
	            throw e;  // 이미 적절한 예외이므로 그대로 전달
	        } catch (Exception e) {
	            log.error("카테고리별 상세 정보 저장 중 오류: {}", e.getMessage());
	            throw new DatabaseException("카테고리별 상세 정보 저장 중 오류가 발생했습니다: " + e.getMessage());
	        }
	        
	    } catch (Exception e) {
	        log.error("컨텐츠 등록 중 오류 발생: {}", e.getMessage(), e);
	        
	        if (e instanceof ServiceException) {
	            throw e;
	        } else {
	            throw new ServiceException("컨텐츠 등록 중 오류가 발생했습니다: " + e.getMessage());
	        }
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

	@Override
	public void updateContent(Long contentId, MainContentReqDTO dto, MultipartFile thumbnail, List<MultipartFile> newFiles, String thumbnailUrl, List<String> deletedImages) {
		
		validateUpdateDto(dto);
		
		try {
			if (contentId == null || contentId <= 0) {
				throw new InvalidRequestException("유효한 contentId가 필요합니다.");
			}
			dto.setContentId(contentId);

			// 1) 기본정보 업데이트
			try {
				contentMapper.updateContent(dto);
			} catch (Exception e) {
				log.error("기본정보 업데이트 실패: {}", e.getMessage(), e);
				throw new DatabaseException("기본정보 업데이트 중 오류가 발생했습니다: " + e.getMessage());
			}
			
			// 2) 썸네일 교체 처리
			try {
				if (thumbnail != null && !thumbnail.isEmpty()) {
					String firstImageUrl = null;
				    // 새 파일 업로드
					firstImageUrl = s3Util.upLoadFiles(thumbnail);
					
				    String oldThumb = contentMapper.selectContentByContentId(contentId).getThumbnail();
				    if (oldThumb != null) {
				        s3Util.deleteFiles(oldThumb);
				    }
				    String newThumbUrl = s3Util.upLoadFiles(thumbnail);
				    if (newThumbUrl == null || newThumbUrl.isBlank()) {
				        throw new FileUploadException("새 썸네일 업로드에 실패했습니다.");
				    }
				    contentMapper.updateFirstImage(contentId, firstImageUrl);
				} else if (thumbnailUrl != null && !thumbnailUrl.isBlank()) {
					contentMapper.deleteSingleContentImg(contentId, thumbnailUrl);
				    
				    // 기존 이미지 URL을 썸네일로 지정
				    contentMapper.updateFirstImage(contentId, thumbnailUrl);
				}
			} catch (InvalidRequestException | FileUploadException ex) {
				throw ex;
			} catch (Exception e) {
				log.error("썸네일 교체 중 오류: {}", e.getMessage(), e);
				throw new ServiceException("썸네일 교체 중 오류가 발생했습니다: " + e.getMessage());
			}
			
			// 3) 기존 이미지 삭제 처리
			if (deletedImages != null && !deletedImages.isEmpty()) {
				try {
					contentMapper.deleteContentImg(contentId, deletedImages);
					s3Util.deleteFiles(deletedImages);
				} catch (Exception e) {
					log.error("기존 이미지 삭제 중 오류: {}", e.getMessage(), e);
					throw new DatabaseException("기존 이미지 삭제 중 오류가 발생했습니다: " + e.getMessage());
				}
			}

			// 4) 새 이미지 업로드 및 DB 저장
			if (newFiles != null && !newFiles.isEmpty()) {
				try {
					List<String> uploaded = s3Util.upLoadFiles(newFiles);
					if (uploaded.isEmpty()) {
						throw new FileUploadException("새 이미지 업로드 실패");
					}
					for (String url : uploaded) {
						contentMapper.addContentImg(
								FileUrl.builder()
								.contentId(contentId)
								.fileUrl(url)
								.build()
								);
					}
				} catch (InvalidRequestException | FileUploadException ex) {
					throw ex;
				} catch (Exception e) {
					log.error("새 이미지 등록 중 오류: {}", e.getMessage(), e);
					throw new ServiceException("새 이미지 등록 중 오류가 발생했습니다: " + e.getMessage());
				}
			}

			// 5) 상세주소 정보 업데이트
			try {
				Long dongNo = contentMapper.selectDongNoByRegionName(dto);
				if (dongNo == null) {
					throw new InvalidRequestException("유효한 지역 정보가 아닙니다.");
				}
				contentMapper.updateDetailAdd(
						DetailAdd.builder()
						.contentId(contentId)
						.dongNo(dongNo)
						.detailName(dto.getDetailName())
						.postAddress(dto.getPostAddress())
						.build()
						);
			} catch (InvalidRequestException ex) {
				throw ex;
			} catch (Exception e) {
				log.error("상세주소 업데이트 중 오류: {}", e.getMessage(), e);
				throw new DatabaseException("상세주소 업데이트 중 오류가 발생했습니다: " + e.getMessage());
			}

			// 6) 맵 좌표 업데이트
			try {
				if (dto.getMapX() == null || dto.getMapY() == null) {
					throw new InvalidRequestException("X,Y 좌표가 모두 필요합니다.");
				}
				contentMapper.updateMapXY(
						MapXY.builder()
						.contentId(contentId)
						.mapX(dto.getMapX())
						.mapY(dto.getMapY())
						.build()
						);
			} catch (InvalidRequestException ex) {
				throw ex;
			} catch (Exception e) {
				log.error("맵 좌표 업데이트 중 오류: {}", e.getMessage(), e);
				throw new DatabaseException("맵 좌표 업데이트 중 오류가 발생했습니다: " + e.getMessage());
			}

			// 7) 카테고리별 상세 업데이트
			try {
				switch (dto.getCategoryCode()) {
				case 1:
					contentMapper.updateTour(
							Tour.builder()
							.contentId(contentId)
							.tourExp(dto.getTourExp())
							.usetimeTour(dto.getUseTimeTour())
							.parking(dto.getParking())
							.build()
							);
					break;
				case 2:
					contentMapper.updateFood(
							Food.builder()
							.contentId(contentId)
							.foodExp(dto.getFoodExp())
							.mainMenu(dto.getMainMenu())
							.parking(dto.getParking())
							.build()
							);
					break;
				case 3:
					contentMapper.updateLodging(
							Lodging.builder()
							.contentId(contentId)
							.lodgingExp(dto.getLodgingExp())
							.checkIn(dto.getCheckIn())
							.checkOut(dto.getCheckOut())
							.parking(dto.getParking())
							.elevator(dto.getElevator())
							.build()
							);
					break;
				case 4:
					contentMapper.updateFestival(
							Festival.builder()
							.contentId(contentId)
							.program(dto.getProgram())
							.eventExp(dto.getEventExp())
							.sponsor(dto.getSponsor())
							.useTimeFestival(dto.getUseTimeFestival())
							.eventStartDate(dto.getEventStartDate())
							.eventEndDate(dto.getEventEndDate())
							.build()
							);
					break;
				default:
					throw new InvalidRequestException(
							"유효하지 않은 카테고리 코드: " + dto.getCategoryCode()
							);
				}
			} catch (InvalidRequestException ex) {
				throw ex;
			} catch (Exception e) {
				log.error("카테고리별 상세 업데이트 중 오류: {}", e.getMessage(), e);
				throw new DatabaseException("카테고리별 상세 업데이트 중 오류가 발생했습니다: " + e.getMessage());
			}

		} catch (InvalidRequestException | FileUploadException | DatabaseException ex) {
			// 이미 의미 있는 예외이므로 그대로 던집니다
			throw ex;
		} catch (Exception e) {
			log.error("컨텐츠 업데이트 중 예기치 않은 오류: {}", e.getMessage(), e);
			throw new ServiceException("컨텐츠 업데이트 중 오류가 발생했습니다: " + e.getMessage());
		}
	}

    private void validateUpdateDto(MainContentReqDTO dto) {

        switch (dto.getCategoryCode()) {
            case 1: // 관광지
                if (dto.getTourExp() == null || dto.getTourExp().isBlank()) {
                    throw new InvalidRequestException("투어 설명은 필수입니다.");
                }
                if (dto.getUseTimeTour() == null || dto.getUseTimeTour().isBlank()) {
                    throw new InvalidRequestException("투어 이용 시간은 필수입니다.");
                }
                if (dto.getParking() == null || !dto.getParking().matches("^[YN]$")) {
                    throw new InvalidRequestException("주차 가능 여부는 'Y' 또는 'N'이어야 합니다.");
                }
                break;

            case 2: // 맛집
                if (dto.getFoodExp() == null || dto.getFoodExp().isBlank()) {
                    throw new InvalidRequestException("음식 설명은 필수입니다.");
                }
                if (dto.getMainMenu() == null || dto.getMainMenu().isBlank()) {
                    throw new InvalidRequestException("메인 메뉴는 필수입니다.");
                }
                if (dto.getParking() == null || !dto.getParking().matches("^[YN]$")) {
                    throw new InvalidRequestException("주차 가능 여부는 'Y' 또는 'N'이어야 합니다.");
                }
                break;

            case 3: // 숙소
                if (dto.getLodgingExp() == null || dto.getLodgingExp().isBlank()) {
                    throw new InvalidRequestException("숙소 설명은 필수입니다.");
                }
                if (dto.getCheckIn() == null || dto.getCheckIn().isBlank()) {
                    throw new InvalidRequestException("체크인 날짜는 필수입니다.");
                }
                if (dto.getCheckOut() == null || dto.getCheckOut().isBlank()) {
                    throw new InvalidRequestException("체크아웃 날짜는 필수입니다.");
                }
                if (dto.getElevator() == null || !dto.getElevator().matches("^[YN]$")) {
                    throw new InvalidRequestException("엘리베이터 옵션은 'Y' 또는 'N'이어야 합니다.");
                }
                break;

            case 4: // 축제
                if (dto.getProgram() == null || dto.getProgram().isBlank()) {
                    throw new InvalidRequestException("프로그램명은 필수입니다.");
                }
                if (dto.getEventExp() == null || dto.getEventExp().isBlank()) {
                    throw new InvalidRequestException("이벤트 설명은 필수입니다.");
                }
                if (dto.getSponsor() == null || dto.getSponsor().isBlank()) {
                    throw new InvalidRequestException("주최사는 필수입니다.");
                }
                if (dto.getUseTimeFestival() == null || dto.getUseTimeFestival().isBlank()) {
                    throw new InvalidRequestException("축제 이용 시간은 필수입니다.");
                }
                if (dto.getEventStartDate() == null) {
                    throw new InvalidRequestException("이벤트 시작일은 필수입니다.");
                }
                if (dto.getEventEndDate() == null) {
                    throw new InvalidRequestException("이벤트 종료일은 필수입니다.");
                }
                break;

            default:
                throw new InvalidRequestException("알 수 없는 카테고리 코드입니다.");
        }
    }

	@Override
	public void deleteContentByContentId(Long contentId, String status) {
		
		String getStatus = contentMapper.selectContentByContentId(contentId).getStatus();
		
		if("Y".equals(getStatus)) {
			status ="N";
		} else {
			status ="Y";
		}
		
		contentMapper.deleteContentByContentId(contentId, status);
	}
	
}
