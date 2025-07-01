package com.kh.finalproject.chat.model.dto;

import java.time.LocalDateTime;

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
public class MessageDTO {
    private Long chatMessageNo;
    private Long roomNo;
    private Long userNo;
    private String messageContent;
    private String nickname;
    private String realname;
    private LocalDateTime createdDate;
}
