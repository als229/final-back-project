package com.kh.finalproject.global.log.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
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
