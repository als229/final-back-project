package com.kh.finalproject.mainContent.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.finalproject.mainContent.model.dto.ContentSearchDTO;
import com.kh.finalproject.mainContent.model.dto.MainContentReqDTO;
import com.kh.finalproject.mainContent.model.dto.MainContentResDTO;
import com.kh.finalproject.mainContent.model.vo.Content;
import com.kh.finalproject.mainContent.model.vo.DetailAdd;
import com.kh.finalproject.mainContent.model.vo.Festival;
import com.kh.finalproject.mainContent.model.vo.FileUrl;
import com.kh.finalproject.mainContent.model.vo.Food;
import com.kh.finalproject.mainContent.model.vo.Lodging;
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
}
