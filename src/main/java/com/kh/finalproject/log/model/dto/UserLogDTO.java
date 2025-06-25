package com.kh.finalproject.log.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLogDTO {

  private Long logNo;
  private Long userNo;
  private String userName;
  private String oldInfo;
  private String newInfo;
  private Date createdTime; 
}
