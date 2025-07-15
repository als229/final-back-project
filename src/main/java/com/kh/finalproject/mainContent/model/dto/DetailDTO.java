package com.kh.finalproject.mainContent.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetailDTO {
    private String program;             
    private String eventExp;            
    private String sponsor;             
    private String useTimeFestival;     
    private Date eventStartDate;   
    private Date eventEndDate;     

    // === FOOD (맛집) ===
    private String foodExp;           
    private String mainMenu;          

    // === LODGING (숙소) ===
    private String lodgingExp;        
    private String checkIn;           
    private String checkOut;          
    private String elevator;         

    // === TOUR (관광지) ===
    private String tourExp;           
    private String useTimeTour;       

    // === 공통 필드 ===
    private String parking;          
}
