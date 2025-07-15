package com.kh.finalproject.mainContent.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

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

@Mapper
public interface MainContentMapper {
    void addContent(Content content);
    
    Long selectContentId();

    Long selectDongNoByRegionName(MainContentReqDTO dto);

    int addDetailAdd(DetailAdd detailAdd);

    int addContentImg(FileUrl requestFileUrl);

    int addFestival(Festival festival);

    int addFood(Food food);

    int addLodging(Lodging lodging);

    int addTour(Tour tour);
    
    List<MainContentResDTO> selectContentCardList(ContentSearchDTO searchDto, RowBounds rb);
    
    int selectContentCardCount(ContentSearchDTO searchDto);
    
    MainContentResDTO selectContentByContentId(Long contentId);
    
    DetailDTO selectFestivalByContentId(Long contentId);
    DetailDTO selectFoodByContentId(Long contentId);
    DetailDTO selectLodgingByContentId(Long contentId);
    DetailDTO selectTourByContentId(Long contentId);
    
    void addMapXY(MapXY mapXY);
    
    // update 관련
    int updateContent(MainContentReqDTO dto);

    int updateFirstImage(@Param("contentId") Long contentId, @Param("firstImage") String firstImage);

    int deleteContentImg(@Param("contentId")Long contentId,@Param("fileUrls") List<String> fileUrls);
    
    int deleteSingleContentImg(@Param("contentId")Long contentId,@Param("fileUrl") String fileUrl);

    int updateDetailAdd(DetailAdd detail);

    int updateMapXY(MapXY mapXY);

    int updateTour(Tour tour);
    int updateFood(Food food);
    int updateLodging(Lodging lodging);
    int updateFestival(Festival fest);
    
    void deleteContentByContentId(@Param("contentId")Long contentId, @Param("status")String status);
}
