package com.kh.finalproject.content.model.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.finalproject.content.model.dao.ContentImgMapper;
import com.kh.finalproject.content.model.dto.ContentImgDTO;
import com.kh.finalproject.content.model.vo.ContentImg;
import com.kh.finalproject.exception.exceptions.FileUploadException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContentImgServiceImpl implements ContentImgService{
	
    private final ContentImgMapper contentImgMapper;
    
    @Value("${upload.path}")
    private String uploadDir;
    
    @Override
    public String uploadImageOnly(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("업로드할 파일이 없습니다.");
        }

        String originalFileName = file.getOriginalFilename();
        String savedFileName = UUID.randomUUID() + "_" + originalFileName;
        String filePath = uploadDir + File.separator + savedFileName;

        File dest = new File(filePath);
        dest.getParentFile().mkdirs();

        try {
            file.transferTo(dest);
        } catch (IOException e) {
            log.error("파일 저장 실패: {}", e.getMessage(), e);
            throw new FileUploadException("대표 이미지 업로드 실패");
        }

        return filePath;
    }

    
    @Override
    public void uploadAndInsertImages(List<MultipartFile> files, Long contentId) {
    	System.out.println(">>> 전달되는 contentId: " + contentId);
        if (files == null || files.isEmpty()) {
            throw new IllegalArgumentException("업로드할 파일이 없습니다.");
        }

        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) {
                continue;
            }

            String originalFileName = file.getOriginalFilename();
            String savedFileName = UUID.randomUUID() + "_" + originalFileName;
            String filePath = uploadDir + File.separator + savedFileName;

            File dest = new File(filePath);
            dest.getParentFile().mkdirs();

            try {
                file.transferTo(dest);
            } catch (IOException e) {
                log.error("파일 저장 실패: {}", e.getMessage(), e);
                throw new FileUploadException("이미지 업로드에 실패했습니다. 관리자에게 문의하세요.");
            }

            ContentImg contentImg = ContentImg.builder()
                    .contentId(contentId)
                    .fileUrl(filePath)
                    .build();

            contentImgMapper.insertContentImg(contentImg);
        }
    }
    
    

}
